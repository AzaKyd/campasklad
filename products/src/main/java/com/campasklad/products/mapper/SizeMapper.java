package com.campasklad.products.mapper;

import com.campasklad.products.dto.SizeDto;
import com.campasklad.products.entity.Size;
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
