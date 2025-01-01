package com.campasklad.facility.service.impl;

import com.campasklad.facility.dto.FacilityProductDto;
import com.campasklad.facility.enitity.Facility;
import com.campasklad.facility.excetion.BaseException;
import com.campasklad.facility.excetion.ExceptionType;
import com.campasklad.facility.mapper.FacilityProductMapper;
import com.campasklad.facility.repository.FaciltiyRepository;
import com.campasklad.facility.repository.FacilityProductRepository;
import com.campasklad.facility.service.FacilityProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FacilityProductServiceImpl implements FacilityProductService {

    FacilityProductRepository facilityProductRepository;
    FaciltiyRepository faciltiyRepository;
    FacilityProductMapper facilityProductMapper;

    @Override
    public void createInventory(FacilityProductDto facilityProductDto) {
        Facility facility = faciltiyRepository.findById(facilityProductDto.getFacilityId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));
        facilityProductRepository.save(facilityProductMapper.toEntity(facilityProductDto, facility));
    }
}
