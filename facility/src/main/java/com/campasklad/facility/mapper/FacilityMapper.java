package com.campasklad.facility.mapper;

import com.campasklad.facility.dto.FacilityDto;
import com.campasklad.facility.entity.Facility;
import org.springframework.stereotype.Component;

@Component
public class FacilityMapper {
    public Facility toEntity(FacilityDto facilityDto) {
        return Facility.builder()
                .id(facilityDto.getId())
                .name(facilityDto.getName())
                .location(facilityDto.getLocation())
                .build();
    }

    public FacilityDto toDto(Facility facility) {
        return FacilityDto.builder()
                .id(facility.getId())
                .name(facility.getName())
                .location(facility.getLocation())
                .build();
    }
}
