package com.campasklad.products.service;

import com.campasklad.products.dto.ProductDto;
import com.campasklad.products.dto.SeasonDto;

import java.util.List;

public interface ProductService {

    void createProduct(ProductDto productDto);
    ProductDto getProductById(Long id);

    void updateProduct(ProductDto productDto);
    void deleteProduct(Long id);
}
