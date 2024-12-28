package com.campasklad.products.mapper;

import com.campasklad.products.dto.SeasonDto;
import com.campasklad.products.dto.SizeDto;
import com.campasklad.products.entity.Season;
import com.campasklad.products.entity.Size;
import org.springframework.stereotype.Component;

@Component
public class SeasonMapper {
    public SeasonDto toDto(Season season) {
        return SeasonDto.builder()
                .id(season.getId())
                .name(season.getName())
                .build();
    }

    public Season toEntity(SeasonDto seasonDto) {
        return Season.builder()
                .id(seasonDto.getId())
                .name(seasonDto.getName())
                .build();
    }
}
