package com.campasklad.products.mapper;

import com.campasklad.products.dto.CategoryDto;
import com.campasklad.products.entity.Category;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryDto categoryDto) {
        if(Objects.isNull(categoryDto)){
            return null;
        }
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }

    public CategoryDto toDto(Category category) {
        if(Objects.isNull(category)){
            return null;
        }
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
