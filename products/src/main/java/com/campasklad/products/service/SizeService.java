package com.campasklad.products.service;

import com.campasklad.products.dto.SizeDto;

import java.util.List;

public interface SizeService {

    void createSize(SizeDto sizeDto);
    SizeDto getSizeById(Long id);
    List<SizeDto> getAllSeasons();
    void updateSize(SizeDto sizeDto);
    void deleteSize(Long id);
}
