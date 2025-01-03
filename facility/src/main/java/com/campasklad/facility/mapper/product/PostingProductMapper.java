package com.campasklad.facility.mapper.product;

import com.campasklad.facility.dto.product.PostingProductDto;
import com.campasklad.facility.entity.ProductVariation;
import com.campasklad.facility.entity.product.PostingProduct;
import org.springframework.stereotype.Component;

@Component
public class PostingProductMapper {

    public PostingProductDto toDto(PostingProduct postingProduct) {
        return PostingProductDto.builder()
                .id(postingProduct.getId())
                .quantity(postingProduct.getQuantity())
                .productVariationId(postingProduct.getProductVariation() != null ? postingProduct.getProductVariation().getId() : null)
                .build();
    }

    public PostingProduct toEntity(PostingProductDto postingProductDto, ProductVariation productVariation) {
        return PostingProduct.builder()
                .id(postingProductDto.getId())
                .quantity(postingProductDto.getQuantity())
                .productVariation(productVariation)
                .build();
    }
}
