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

            List<PostingProduct> postingProducts = postingProductDtos.stream()
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

            Posting savedPosting = postingRepository.save(newPosting);
            postingProducts.forEach(product -> product.setPosting(savedPosting));
            postingProductRepository.saveAll(postingProducts);
        }
    }

    @Override
    public void updatePosting(PostingProductRequestDto postingProductRequestDto) {
        Long postingId = postingProductRequestDto.getPostingDto().getId();
        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        Facility facility = facilityRepository.findById(postingProductRequestDto.getPostingDto().getFacilityId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));


        DocumentStatus status = postingProductRequestDto.getPostingDto().getStatus();

        // TODO
        List<PostingProductDto> postingProductDtos = consolidateProducts(postingProductRequestDto.getPostingProductDtos());

        if (DocumentStatus.NEW.equals(status) && Objects.isNull(postingId)) {
            Posting newPosting = Posting.builder()
                    .status(DocumentStatus.NEW)
                    .facility(facility)
                    .build();

            List<PostingProduct> postingProducts = postingProductDtos.stream()
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

            Posting savedPosting = postingRepository.save(newPosting);
            postingProducts.forEach(product -> product.setPosting(savedPosting));
            postingProductRepository.saveAll(postingProducts);
        }
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
