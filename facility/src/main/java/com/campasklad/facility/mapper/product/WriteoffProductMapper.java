package com.campasklad.facility.mapper.product;

import com.campasklad.facility.dto.product.WriteoffProductDto;
import com.campasklad.facility.entity.product.WriteoffProduct;
import org.springframework.stereotype.Component;

@Component
public class WriteoffProductMapper {
    public WriteoffProductDto toDto(WriteoffProduct writeoffProduct) {
        return WriteoffProductDto.builder()
                .id(writeoffProduct.getId())
                .quantity(writeoffProduct.getQuantity())
                .productVariationId(writeoffProduct.getProductVariationId())
                .build();
    }

    public WriteoffProduct toEntity(WriteoffProductDto writeoffProductDto) {
        return WriteoffProduct.builder()
                .id(writeoffProductDto.getId())
                .quantity(writeoffProductDto.getQuantity())
                .productVariationId(writeoffProductDto.getProductVariationId())
                .build();
    }
}
