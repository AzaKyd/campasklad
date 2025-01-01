package com.campasklad.facility.service;

import com.campasklad.facility.dto.FacilityDto;

public interface FacilityService {

    FacilityDto getFacilityById(Long id);
    void createFacility(FacilityDto facilityDto);
    void updateFacility(FacilityDto facilityDto);
    void deleteFacility(Long id);
}
