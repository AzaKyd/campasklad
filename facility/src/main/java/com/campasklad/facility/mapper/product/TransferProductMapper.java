package com.campasklad.facility.mapper.product;

import com.campasklad.facility.dto.product.TransferProductDto;
import com.campasklad.facility.entity.ProductVariation;
import com.campasklad.facility.entity.product.TransferProduct;
import org.springframework.stereotype.Component;

@Component
public class TransferProductMapper {
    public TransferProductDto toDto(TransferProduct transferProduct) {
        return TransferProductDto.builder()
                .id(transferProduct.getId())
                .quantity(transferProduct.getQuantity())
                .productVariationId(transferProduct.getProductVariation() != null ? transferProduct.getProductVariation().getId() : null)
                .build();
    }

    public TransferProduct toEntity(TransferProductDto transferProductDto, ProductVariation productVariation) {
        return TransferProduct.builder()
                .id(transferProductDto.getId())
                .quantity(transferProductDto.getQuantity())
                .productVariation(productVariation)
                .build();
    }
}
