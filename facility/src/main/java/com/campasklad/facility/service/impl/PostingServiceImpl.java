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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostingServiceImpl implements PostingService {

    PostingRepository postingRepository;
    PostingMapper postingMapper;

    PostingProductRepository postingProductRepository;
    FacilityRepository facilityRepository;
    PostingProductMapper postingProductMapper;
    ProductVariationRepository productVariationRepository;
    private final SizeRepository sizeRepository;
    private final ColorRepository colorRepository;

    @Override
    public void createPostingProduct(PostingProductRequestDto postingProductRequestDto) {
        Facility facility = facilityRepository.findById(postingProductRequestDto.getPostingDto().getFacilityId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        Long postingId = postingProductRequestDto.getPostingDto().getId();
        DocumentStatus status = postingProductRequestDto.getPostingDto().getStatus();

        // TODO
        List<PostingProductDto> postingProductDtos = postingProductRequestDto.getPostingProductDtos().stream()
                .collect(Collectors.groupingBy(
                        product -> Arrays.asList(product.getProductId(), product.getSizeId(), product.getColorId()), // Ключ: уникальная комбинация
                        Collectors.reducing(
                                new PostingProductDto(), // Инициализация
                                product -> product, // Маппер: сам объект
                                (product1, product2) -> { // Суммируем количество
                                    product1.setQuantity(product1.getQuantity() + product2.getQuantity());
                                    return product1;
                                }
                        )
                ))
                .values()
                .stream()
                .filter(dto -> dto.getProductId() != null) // Фильтруем пустые записи (инициализацию)
                .toList();

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
}
