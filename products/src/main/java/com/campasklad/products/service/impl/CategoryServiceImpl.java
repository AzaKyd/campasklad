package com.campasklad.products.service.impl;

import com.campasklad.products.dto.CategoryDto;
import com.campasklad.products.mapper.CategoryMapper;
import com.campasklad.products.repository.CategoryRepository;
import com.campasklad.products.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;


    @Override
    public void createCategory(CategoryDto categoryDto) {
        categoryRepository.save(categoryMapper.toEntity(categoryDto));
    }
}
