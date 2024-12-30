package com.campasklad.products.service;

import com.campasklad.products.dto.ProductVariationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductVariationService {

    void createProductVariation(ProductVariationDto productVariationDto);
    ProductVariationDto getProductVariationById(Long id);
    List<ProductVariationDto> getProductsByProductVariations();
    void updateProductVariation(ProductVariationDto productVariationDto);
    void deleteProductVariation(Long id);
}
