package com.campasklad.facility.service;

import com.campasklad.facility.dto.product.DisplayFacilityProductDto;
import com.campasklad.facility.dto.product.FacilityProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FacilityProductService {

    void createFacilityProduct(FacilityProductDto facilityProductDto);
    Page<DisplayFacilityProductDto> getFacilityProducts(Long facilityId, Pageable pageable);

}
