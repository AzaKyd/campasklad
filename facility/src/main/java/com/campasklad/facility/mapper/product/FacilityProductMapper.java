package com.campasklad.facility.mapper.product;

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
}
