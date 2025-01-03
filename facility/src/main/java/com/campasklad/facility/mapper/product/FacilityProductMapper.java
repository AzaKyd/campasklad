package com.campasklad.facility.mapper.product;

import com.campasklad.facility.dto.internal.ProductIdsDto;
import com.campasklad.facility.dto.internal.ProductResponseDto;
import com.campasklad.facility.dto.product.DisplayFacilityProductDto;
import com.campasklad.facility.dto.product.FacilityProductDto;
import com.campasklad.facility.enitity.Facility;
import com.campasklad.facility.enitity.product.FacilityProduct;
import org.springframework.stereotype.Component;

@Component
public class FacilityProductMapper {

    public FacilityProductDto toDto(FacilityProduct facilityProduct) {
        return FacilityProductDto.builder()
                .id(facilityProduct.getId())
                .productId(facilityProduct.getProductId())
                .quantity(facilityProduct.getQuantity())
                .productVariationId(facilityProduct.getProductVariationId())
                .facilityId(facilityProduct.getFacility() != null ? facilityProduct.getFacility().getId() : null)
                .build();
    }

    public FacilityProduct toEntity(FacilityProductDto facilityProductDto, Facility facility) {
        return FacilityProduct.builder()
                .id(facilityProductDto.getId())
                .productId(facilityProductDto.getProductId())
                .quantity(facilityProductDto.getQuantity())
                .productVariationId(facilityProductDto.getProductVariationId())
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
