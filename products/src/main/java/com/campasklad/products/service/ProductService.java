package com.campasklad.products.service;

import com.campasklad.products.dto.ProductDto;
import com.campasklad.products.dto.ProductFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    void createProduct(ProductDto productDto);
    ProductDto getProductById(Long id);
    void updateProduct(ProductDto productDto);
    void deleteProduct(Long id);

    Page<ProductDto> getFilteredProducts(ProductFilterDto productFilterDto, Pageable pageable);
    List<ProductDto> getProductsByIds(List<Long> ids);
}
