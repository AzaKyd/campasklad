package com.campasklad.products.mapper;

import com.campasklad.products.dto.ProductVariationDto;
import com.campasklad.products.entity.Color;
import com.campasklad.products.entity.Product;
import com.campasklad.products.entity.Size;
import com.campasklad.products.entity.ProductVariation;
import org.springframework.stereotype.Component;

@Component
public class ProductVariationMapper {

    public ProductVariationDto toDto(ProductVariation productVariation) {
        return ProductVariationDto.builder()
                .id(productVariation.getId())
                .productId(productVariation.getProduct() != null ? productVariation.getProduct().getId() : null)
                .sizeId(productVariation.getSize() != null ? productVariation.getSize().getId() : null)
                .colorId(productVariation.getColor() != null ? productVariation.getSize().getId() : null)
                .build();
    }

    public ProductVariation toEntity(ProductVariationDto productVariationDto, Product product, Size size, Color color) {
        return ProductVariation.builder()
                .id(productVariationDto.getId())
                .product(product)
                .size(size)
                .color(color)
                .build();
    }
}
