package com.campasklad.facility.service;

import com.campasklad.facility.dto.ColorDto;

import java.util.List;

public interface ColorService {

    void createColor(ColorDto colorDto);
    ColorDto getCategoryById(Long id);
    List<ColorDto> getAllColors();
    void updateColor(ColorDto categoryDto);
    void deleteColor(Long id);
}
