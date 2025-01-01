package com.campasklad.facility.mapper.product;

import com.campasklad.facility.dto.product.TransferProductDto;
import com.campasklad.facility.enitity.product.TransferProduct;
import org.springframework.stereotype.Component;

@Component
public class TransferProductMapper {
    public TransferProductDto toDto(TransferProduct transferProduct) {
        return TransferProductDto.builder()
                .id(transferProduct.getId())
                .quantity(transferProduct.getQuantity())
                .productVariationId(transferProduct.getProductVariationId())
                .build();
    }

    public TransferProduct toEntity(TransferProductDto transferProductDto) {
        return TransferProduct.builder()
                .id(transferProductDto.getId())
                .quantity(transferProductDto.getQuantity())
                .productVariationId(transferProductDto.getProductVariationId())
                .build();
    }
}
