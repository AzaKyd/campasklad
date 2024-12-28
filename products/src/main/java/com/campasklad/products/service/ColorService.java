package com.campasklad.products.service;

import com.campasklad.products.dto.CategoryDto;
import com.campasklad.products.dto.ColorDto;

import java.util.List;

public interface ColorService {

    void createColor(ColorDto colorDto);
    ColorDto getCategoryById(Long id);
    List<ColorDto> getAllColors();
    void updateColor(ColorDto categoryDto);
    void deleteColor(Long id);
}
