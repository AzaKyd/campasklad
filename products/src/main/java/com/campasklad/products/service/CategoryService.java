package com.campasklad.products.service;

import com.campasklad.products.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    void createCategory(CategoryDto categoryDto);
    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getAllCategories();
    void updateCategory(CategoryDto categoryDto);
    void deleteCategory(Long id);

}
