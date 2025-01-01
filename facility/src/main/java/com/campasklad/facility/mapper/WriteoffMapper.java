package com.campasklad.facility.mapper;

import com.campasklad.facility.dto.WriteoffDto;
import com.campasklad.facility.enitity.Facility;
import com.campasklad.facility.enitity.Writeoff;
import org.springframework.stereotype.Component;

@Component
public class WriteoffMapper {

    public WriteoffDto toDto(Writeoff writeOff) {
        return WriteoffDto.builder()
                .id(writeOff.getId())
                .facilityId(writeOff.getFacility() != null ? writeOff.getFacility().getId() : null)
                .status(writeOff.getStatus())
                .build();
    }

    public Writeoff toEntity(WriteoffDto writeOffDto, Facility facility) {
        return Writeoff.builder()
                .id(writeOffDto.getId())
                .facility(facility)
                .status(writeOffDto.getStatus())
                .build();
    }
}
