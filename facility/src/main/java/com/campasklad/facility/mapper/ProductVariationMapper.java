package com.campasklad.facility.mapper;

import com.campasklad.facility.dto.ProductVariationDto;
import com.campasklad.facility.entity.Color;
import com.campasklad.facility.entity.ProductVariation;
import com.campasklad.facility.entity.Size;
import org.springframework.stereotype.Component;

@Component
public class ProductVariationMapper {

    public ProductVariationDto toDto(ProductVariation productVariation) {
        return ProductVariationDto.builder()
                .id(productVariation.getId())
                .productId(productVariation.getProductId())
                .sizeId(productVariation.getSize() != null ? productVariation.getSize().getId() : null)
                .colorId(productVariation.getColor() != null ? productVariation.getSize().getId() : null)
                .build();
    }

    public ProductVariation toEntity(ProductVariationDto productVariationDto, Size size, Color color) {
        return ProductVariation.builder()
                .id(productVariationDto.getId())
                .productId(productVariationDto.getProductId())
                .size(size)
                .color(color)
                .build();
    }
}
