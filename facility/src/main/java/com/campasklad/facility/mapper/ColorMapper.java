package com.campasklad.facility.mapper;

import com.campasklad.facility.dto.ColorDto;
import com.campasklad.facility.entity.Color;
import org.springframework.stereotype.Component;

@Component
public class ColorMapper {

    public ColorDto toDto(Color color) {
        return ColorDto.builder()
                .id(color.getId())
                .name(color.getName())
                .build();
    }

    public Color toEntity(ColorDto colorDto) {
        return Color.builder()
                .id(colorDto.getId())
                .name(colorDto.getName())
                .build();
    }
}
