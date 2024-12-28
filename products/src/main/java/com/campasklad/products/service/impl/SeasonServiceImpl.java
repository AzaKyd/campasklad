package com.campasklad.products.service.impl;

import com.campasklad.products.dto.SeasonDto;
import com.campasklad.products.entity.Season;
import com.campasklad.products.exception.ExceptionType;
import com.campasklad.products.exception.BaseException;
import com.campasklad.products.mapper.SeasonMapper;
import com.campasklad.products.repository.SeasonRepository;
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

    SeasonRepository seasonRepository;
    SeasonMapper seasonMapper;

    @Override
    public void createSeason(SeasonDto seasonDto) {
        seasonRepository.save(seasonMapper.toEntity(seasonDto));
    }

    @Override
    public SeasonDto getSeasonById(Long id) {
        Season season = seasonRepository.findById(id)
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));
        return seasonMapper.toDto(season);
    }

    @Override
    public List<SeasonDto> getAllSeasons() {
        return seasonRepository.findAll().stream().map(seasonMapper::toDto).toList();
    }

    @Override
    public void updateSeason(SeasonDto seasonDto) {
        Season season = seasonRepository.findById(seasonDto.getId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));
        season.setName(seasonDto.getName());
        seasonRepository.save(season);
    }

    @Override
    public void deleteSeason(Long id) {
        if (!seasonRepository.existsById(id)) {
            throw new BaseException(ExceptionType.ENTITY_NOT_FOUND);
        }
        seasonRepository.deleteById(id);
    }
}
