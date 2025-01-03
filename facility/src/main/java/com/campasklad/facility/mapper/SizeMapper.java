package com.campasklad.facility.mapper;

import com.campasklad.facility.dto.SizeDto;
import com.campasklad.facility.entity.Size;
import org.springframework.stereotype.Component;

@Component
public class SizeMapper {

    public SizeDto toDto(Size size) {
        return SizeDto.builder()
                .id(size.getId())
                .name(size.getName())
                .build();
    }

    public Size toEntity(SizeDto sizeDto) {
        return Size.builder()
                .id(sizeDto.getId())
                .name(sizeDto.getName())
                .build();
    }
}
