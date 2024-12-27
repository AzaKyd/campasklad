package com.campasklad.products.mapper;

import com.campasklad.products.dto.CategoryDto;
import com.campasklad.products.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDto dto);
    CategoryDto toDto(Category entity);
}
