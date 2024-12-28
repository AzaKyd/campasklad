package com.campasklad.products.service;

import com.campasklad.products.dto.ColorDto;
import com.campasklad.products.dto.SeasonDto;

import java.util.List;

public interface SeasonService {

    void createSeason(SeasonDto seasonDto);
    SeasonDto getSeasonById(Long id);
    List<SeasonDto> getAllSeasons();
    void updateSeason(SeasonDto seasonDto);
    void deleteSeason(Long id);
}
