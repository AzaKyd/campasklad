package com.campasklad.facility.mapper.product;

import com.campasklad.facility.dto.internal.ProductIdsDto;
import com.campasklad.facility.dto.internal.ProductResponseDto;
import com.campasklad.facility.dto.product.DisplayFacilityProductDto;
import com.campasklad.facility.dto.product.FacilityProductDto;
import com.campasklad.facility.entity.Facility;
import com.campasklad.facility.entity.ProductVariation;
import com.campasklad.facility.entity.product.FacilityProduct;
import org.springframework.stereotype.Component;

@Component
public class FacilityProductMapper {

    public FacilityProductDto toDto(FacilityProduct facilityProduct) {
        return FacilityProductDto.builder()
                .id(facilityProduct.getId())
                .quantity(facilityProduct.getQuantity())
                .productVariationId(facilityProduct.getProductVariation() != null ? facilityProduct.getProductVariation().getId() : null)
                .facilityId(facilityProduct.getFacility() != null ? facilityProduct.getFacility().getId() : null)
                .build();
    }

    public FacilityProduct toEntity(FacilityProductDto facilityProductDto, Facility facility, ProductVariation productVariation) {
        return FacilityProduct.builder()
                .id(facilityProductDto.getId())
                .quantity(facilityProductDto.getQuantity())
                .productVariation(productVariation)
                .facility(facility)
                .build();
    }

    public DisplayFacilityProductDto toDisplayDto(ProductResponseDto productResponseDto, ProductIdsDto productIdsDto) {
        return DisplayFacilityProductDto.builder()
                .id(productResponseDto.getId())
                .name(productResponseDto.getName())
                .barcode(productResponseDto.getBarcode())
                .code(productResponseDto.getCode())
                .costPrice(productResponseDto.getCostPrice())
                .sellingPrice(productResponseDto.getSellingPrice())
                .picturePath(productResponseDto.getPicturePath())
                .description(productResponseDto.getDescription())
                .categoryId(productResponseDto.getCategoryId())
                .categoryName(productResponseDto.getCategoryName())
                .supplierId(productResponseDto.getSupplierId())
                .seasonId(productResponseDto.getSeasonId())
                .totalQuantity(productIdsDto.getTotalQuantity())
                .build();
    }


}
