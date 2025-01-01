package com.campasklad.facility.mapper.product;

import com.campasklad.facility.dto.product.PostingProductDto;
import com.campasklad.facility.enitity.product.PostingProduct;
import org.springframework.stereotype.Component;

@Component
public class PostingProductMapper {

    public PostingProductDto toDto(PostingProduct postingProduct) {
        return PostingProductDto.builder()
                .id(postingProduct.getId())
                .quantity(postingProduct.getQuantity())
                .productVariationId(postingProduct.getProductVariationId())
                .build();
    }

    public PostingProduct toEntity(PostingProductDto postingProductDto) {
        return PostingProduct.builder()
                .id(postingProductDto.getId())
                .quantity(postingProductDto.getQuantity())
                .productVariationId(postingProductDto.getProductVariationId())
                .build();
    }
}
