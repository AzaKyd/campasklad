package com.campasklad.facility.mapper;

import com.campasklad.facility.dto.WriteOffDto;
import com.campasklad.facility.enitity.Facility;
import com.campasklad.facility.enitity.WriteOff;
import org.springframework.stereotype.Component;

@Component
public class WriteOffMapper {

    public WriteOffDto toDto(WriteOff writeOff) {
        return WriteOffDto.builder()
                .id(writeOff.getId())
                .facilityId(writeOff.getFacility() != null ? writeOff.getFacility().getId() : null)
                .productVariationId(writeOff.getProductVariationId())
                .quantity(writeOff.getQuantity())
                .build();
    }

    public WriteOff toEntity(WriteOffDto writeOffDto, Facility facility) {
        return WriteOff.builder()
                .id(writeOffDto.getId())
                .facility(facility)
                .productVariationId(writeOffDto.getProductVariationId())
                .quantity(writeOffDto.getQuantity())
                .build();
    }
}
