package com.campasklad.products.service.impl;

import com.campasklad.products.dto.SeasonDto;
import com.campasklad.products.service.SeasonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeasonServiceImpl implements SeasonService {
    @Override
    public void createSeason(SeasonDto seasonDto) {

    }

    @Override
    public SeasonDto getSeasonById(Long id) {
        return null;
    }

    @Override
    public List<SeasonDto> getAllSeasons() {
        return List.of();
    }

    @Override
    public void updateSeason(SeasonDto seasonDto) {

    }

    @Override
    public void deleteSeason(Long id) {

    }
}
