package com.campasklad.products.service.impl;

import com.campasklad.products.dto.CategoryDto;
import com.campasklad.products.entity.Category;
import com.campasklad.products.exception.ApplicationExceptionType;
import com.campasklad.products.exception.BaseServiceException;
import com.campasklad.products.mapper.CategoryMapper;
import com.campasklad.products.repository.CategoryRepository;
import com.campasklad.products.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(() -> new BaseServiceException(ApplicationExceptionType.ENTITY_NOT_FOUND));
        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryDto.getId()).
                orElseThrow(() -> new BaseServiceException(ApplicationExceptionType.ENTITY_NOT_FOUND));
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new BaseServiceException(ApplicationExceptionType.ENTITY_NOT_FOUND);
        }
        categoryRepository.deleteById(id);
    }
}
