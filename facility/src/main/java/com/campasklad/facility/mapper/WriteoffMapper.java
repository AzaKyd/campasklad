package com.campasklad.facility.mapper;

import com.campasklad.facility.dto.WriteoffDto;
import com.campasklad.facility.entity.Facility;
import com.campasklad.facility.entity.Writeoff;
import com.campasklad.facility.enums.WriteoffReason;
import org.springframework.stereotype.Component;

@Component
public class WriteoffMapper {

    // Преобразование из Entity в DTO
    public WriteoffDto toDto(Writeoff writeOff) {
        return WriteoffDto.builder()
                .id(writeOff.getId())
                .facilityId(writeOff.getFacility() != null ? writeOff.getFacility().getId() : null)
                .status(writeOff.getStatus())
                .massage(writeOff.getMassage())
                .writeoffReason(writeOff.getWriteoffReason() != null ? writeOff.getWriteoffReason() : null)
                .build();
    }

    // Преобразование из DTO в Entity
    public Writeoff toEntity(WriteoffDto writeOffDto, Facility facility) {
        return Writeoff.builder()
                .id(writeOffDto.getId())
                .facility(facility)
                .status(writeOffDto.getStatus())
                .massage(writeOffDto.getMassage())
                .writeoffReason(writeOffDto.getWriteoffReason())
                .build();
    }
}
