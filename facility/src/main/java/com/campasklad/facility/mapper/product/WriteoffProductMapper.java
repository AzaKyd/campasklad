package com.campasklad.facility.mapper.product;

import com.campasklad.facility.dto.product.WriteoffProductDto;
import com.campasklad.facility.entity.ProductVariation;
import com.campasklad.facility.entity.product.WriteoffProduct;
import org.springframework.stereotype.Component;

@Component
public class WriteoffProductMapper {
    public WriteoffProductDto toDto(WriteoffProduct writeoffProduct) {
        return WriteoffProductDto.builder()
                .id(writeoffProduct.getId())
                .quantity(writeoffProduct.getQuantity())
                .productVariationId(writeoffProduct.getProductVariation() != null ? writeoffProduct.getProductVariation().getId() : null)
                .build();
    }

    public WriteoffProduct toEntity(WriteoffProductDto writeoffProductDto, ProductVariation productVariation) {
        return WriteoffProduct.builder()
                .id(writeoffProductDto.getId())
                .quantity(writeoffProductDto.getQuantity())
                .productVariation(productVariation)
                .build();
    }
}
