package com.campasklad.facility.service.impl;

import com.campasklad.facility.dto.product.PostingProductDto;
import com.campasklad.facility.dto.request.PostingProductRequestDto;
import com.campasklad.facility.entity.*;
import com.campasklad.facility.entity.product.PostingProduct;
import com.campasklad.facility.enums.DocumentStatus;
import com.campasklad.facility.exception.BaseException;
import com.campasklad.facility.exception.ExceptionType;
import com.campasklad.facility.mapper.PostingMapper;
import com.campasklad.facility.mapper.product.PostingProductMapper;
import com.campasklad.facility.repository.*;
import com.campasklad.facility.repository.product.PostingProductRepository;
import com.campasklad.facility.service.PostingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostingServiceImpl implements PostingService {

    PostingRepository postingRepository;

    PostingProductRepository postingProductRepository;
    FacilityRepository facilityRepository;
    PostingProductMapper postingProductMapper;
    ProductVariationRepository productVariationRepository;
    SizeRepository sizeRepository;
    ColorRepository colorRepository;
    private final PostingMapper postingMapper;

    @Override
    public void createPostingProduct(PostingProductRequestDto postingProductRequestDto) {
        Facility facility = facilityRepository.findById(postingProductRequestDto.getPostingDto().getFacilityId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        Long postingId = postingProductRequestDto.getPostingDto().getId();
        DocumentStatus status = postingProductRequestDto.getPostingDto().getStatus();

        // TODO
        List<PostingProductDto> postingProductDtos = consolidateProducts(postingProductRequestDto.getPostingProductDtos());

        if (DocumentStatus.NEW.equals(status) && Objects.isNull(postingId)) {
            Posting newPosting = Posting.builder()
                    .status(DocumentStatus.NEW)
                    .facility(facility)
                    .build();

            List<PostingProduct> postingProducts = processCreatePostingProductDto(postingProductDtos);

            Posting savedPosting = postingRepository.save(newPosting);
            postingProducts.forEach(product -> product.setPosting(savedPosting));
            postingProductRepository.saveAll(postingProducts);
        }
    }

    @Override
    public void updatePosting(PostingProductRequestDto postingProductRequestDto) {
        // Validate and fetch the Posting
        Long postingId = postingProductRequestDto.getPostingDto().getId();
        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        // Validate and fetch the Facility
        Facility facility = facilityRepository.findById(posting.getFacility().getId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        // Fetch the current PostingProducts
        List<PostingProduct> currentPostingProducts = postingProductRepository.findAllByPostingId(postingId);

        // Extract the new PostingProducts from the DTO
        List<PostingProductDto> updatedProductsDto = consolidateProducts(postingProductRequestDto.getPostingProductDtos());

        // Map current PostingProducts by ProductVariation ID for easy lookup
        Map<Long, PostingProduct> currentProductsMap = currentPostingProducts.stream()
                .collect(Collectors.toMap(
                        postingProduct -> postingProduct.getProductVariation().getId(),
                        Function.identity()
                ));

        // Prepare lists for updates and new entries
        List<PostingProduct> toUpdate = new ArrayList<>();
        List<PostingProduct> toAdd = new ArrayList<>();
        Set<Long> updatedProductVariationIds = new HashSet<>();

        for (PostingProductDto productDto : updatedProductsDto) {
            // Извлекаем размер
            Size size = sizeRepository.findById(productDto.getSizeId())
                    .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

            // Извлекаем цвет
            Color color = colorRepository.findById(productDto.getColorId())
                    .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

            // Ищем ProductVariation или создаем новый
            ProductVariation productVariation = productVariationRepository
                    .findProductVariationByProductIdAndSizeAndColor(productDto.getProductId(), size, color)
                    .orElseGet(() -> {
                        ProductVariation newVariation = ProductVariation.builder()
                                .productId(productDto.getProductId())
                                .size(size)
                                .color(color)
                                .build();
                        return productVariationRepository.save(newVariation); // Сохраняем новый ProductVariation
                    });

            Long variationId = productVariation.getId();
            updatedProductVariationIds.add(variationId);

            if (currentProductsMap.containsKey(variationId)) {
                // Обновляем существующий PostingProduct
                PostingProduct existingProduct = currentProductsMap.get(variationId);
                existingProduct.setQuantity(productDto.getQuantity());
                toUpdate.add(existingProduct);
            } else {
                // Добавляем новый PostingProduct
                PostingProduct newProduct = PostingProduct.builder()
                        .posting(posting)
                        .productVariation(productVariation)
                        .quantity(productDto.getQuantity())
                        .build();
                toAdd.add(newProduct);
            }
        }

        // Identify and delete removed PostingProducts
        List<PostingProduct> toDelete = currentPostingProducts.stream()
                .filter(product -> !updatedProductVariationIds.contains(product.getProductVariation().getId()))
                .collect(Collectors.toList());

        // Save changes
        postingProductRepository.deleteAll(toDelete);
        postingProductRepository.saveAll(toAdd);
        postingProductRepository.saveAll(toUpdate);

        // Update Posting (if needed) and save
        posting.setFacility(facility);
        postingRepository.save(posting);
    }


    @Override
    public PostingProductRequestDto getPostingProducts(Long id) {
        Posting posting = postingRepository.findById(id)
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));
        List<PostingProductDto> postingProductDtos = postingProductRepository.findAllByPostingId(posting.getId())
                .stream().map(postingProductMapper::toDto).toList();

        PostingProductRequestDto postingProductRequestDto = new PostingProductRequestDto();
        postingProductRequestDto.setPostingDto(postingMapper.toDto(posting));
        postingProductRequestDto.setPostingProductDtos(postingProductDtos);

        return postingProductRequestDto;
    }



    private List<PostingProduct> processCreatePostingProductDto(List<PostingProductDto> postingProductDtos){
        return postingProductDtos.stream()
                .map(dto -> {
                    Size size = sizeRepository.findById(dto.getSizeId())
                            .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

                    Color color = colorRepository.findById(dto.getColorId())
                            .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

                    ProductVariation productVariation = productVariationRepository
                            .findProductVariationByProductIdAndSizeAndColor(dto.getProductId(), size, color)
                            .orElseGet(() -> {
                                ProductVariation newVariation = ProductVariation.builder()
                                        .productId(dto.getProductId())
                                        .size(size)
                                        .color(color)
                                        .build();
                                return productVariationRepository.save(newVariation); // Сохраняем новый ProductVariation
                            });

                    return postingProductMapper.toEntity(dto, productVariation);
                })
                .toList();
    }


    private List<PostingProductDto> consolidateProducts(List<PostingProductDto> postingProductRequestDto) {
        Map<String, PostingProductDto> productMap = new HashMap<>();

        for (PostingProductDto dto : postingProductRequestDto) {
            String key = dto.getProductId() + "-" + dto.getSizeId() + "-" + dto.getColorId();

            if (productMap.containsKey(key)) {
                // Если объект с таким ключом уже существует, суммируем количество
                PostingProductDto existing = productMap.get(key);
                existing.setQuantity(existing.getQuantity() + dto.getQuantity());
            } else {
                // Если объекта нет, добавляем новый
                productMap.put(key, dto);
            }
        }

        return new ArrayList<>(productMap.values());
    }
}
