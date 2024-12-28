package com.campasklad.products.service.impl;

import com.campasklad.products.dto.ColorDto;
import com.campasklad.products.entity.Color;
import com.campasklad.products.exception.ApplicationExceptionType;
import com.campasklad.products.exception.BaseServiceException;
import com.campasklad.products.mapper.ColorMapper;
import com.campasklad.products.repository.ColorRepository;
import com.campasklad.products.service.ColorService;
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
                .orElseThrow(() -> new BaseServiceException(ApplicationExceptionType.ENTITY_NOT_FOUND));
        return colorMapper.toDto(color);
    }

    @Override
    public List<ColorDto> getAllColors() {
        return colorRepository.findAll().stream().map(colorMapper::toDto).toList();
    }

    @Override
    public void updateColor(ColorDto categoryDto) {
        Color color = colorRepository.findById(categoryDto.getId())
                .orElseThrow(() -> new BaseServiceException(ApplicationExceptionType.ENTITY_NOT_FOUND));
        color.setName(categoryDto.getName());
        colorRepository.save(color);
    }

    @Override
    public void deleteColor(Long id) {
        if (!colorRepository.existsById(id)) {
            throw new BaseServiceException(ApplicationExceptionType.ENTITY_NOT_FOUND);
        }
        colorRepository.deleteById(id);
    }
}
