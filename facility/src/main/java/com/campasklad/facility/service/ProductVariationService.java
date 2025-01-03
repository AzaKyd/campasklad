package com.campasklad.facility.service;

import com.campasklad.facility.dto.ProductVariationDto;

import java.util.List;

public interface ProductVariationService {

    void createProductVariation(ProductVariationDto productVariationDto);
    ProductVariationDto getProductVariationById(Long id);
    List<ProductVariationDto> getProductsByProductVariations();
    void updateProductVariation(ProductVariationDto productVariationDto);
    void deleteProductVariation(Long id);
}
