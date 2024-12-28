package com.campasklad.products.mapper;

import com.campasklad.products.dto.ColorDto;
import com.campasklad.products.entity.Color;
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
