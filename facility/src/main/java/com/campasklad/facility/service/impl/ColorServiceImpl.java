package com.campasklad.facility.service.impl;

import com.campasklad.facility.dto.ColorDto;
import com.campasklad.facility.entity.Color;
import com.campasklad.facility.exception.BaseException;
import com.campasklad.facility.exception.ExceptionType;
import com.campasklad.facility.mapper.ColorMapper;
import com.campasklad.facility.repository.ColorRepository;
import com.campasklad.facility.service.ColorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ColorServiceImpl implements ColorService {

    ColorRepository colorRepository;
    ColorMapper colorMapper;

    @Override
    public void createColor(ColorDto colorDto) {
        colorRepository.save(colorMapper.toEntity(colorDto));
    }

    @Override
    public ColorDto getCategoryById(Long id) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));
        return colorMapper.toDto(color);
    }

    @Override
    public List<ColorDto> getAllColors() {
        return colorRepository.findAll().stream().map(colorMapper::toDto).toList();
    }

    @Override
    public void updateColor(ColorDto categoryDto) {
        Color color = colorRepository.findById(categoryDto.getId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));
        color.setName(categoryDto.getName());
        colorRepository.save(color);
    }

    @Override
    public void deleteColor(Long id) {
        if (!colorRepository.existsById(id)) {
            throw new BaseException(ExceptionType.ENTITY_NOT_FOUND);
        }
        colorRepository.deleteById(id);
    }
}
