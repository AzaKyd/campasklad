package com.campasklad.facility.service.impl;

import com.campasklad.facility.dto.FacilityDto;
import com.campasklad.facility.entity.Facility;
import com.campasklad.facility.exception.BaseException;
import com.campasklad.facility.exception.ExceptionType;
import com.campasklad.facility.mapper.FacilityMapper;
import com.campasklad.facility.repository.FaciltiyRepository;
import com.campasklad.facility.service.FacilityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FacilityServiceImpl implements FacilityService {

    FaciltiyRepository facilityRepository;
    FacilityMapper facilityMapper;


    @Override
    public FacilityDto getFacilityById(Long id) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));
        return facilityMapper.toDto(facility);
    }

    @Override
    public void createFacility(FacilityDto facilityDto) {
        facilityRepository.save(facilityMapper.toEntity(facilityDto));
    }

    @Override
    public void updateFacility(FacilityDto facilityDto) {
        Facility facility = facilityRepository.findById(facilityDto.getId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));
        facility.setName(facilityDto.getName());
        facility.setLocation(facilityDto.getLocation());

        facilityRepository.save(facility);
    }

    @Override
    public void deleteFacility(Long id) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));
        facilityRepository.delete(facility);
    }
}
