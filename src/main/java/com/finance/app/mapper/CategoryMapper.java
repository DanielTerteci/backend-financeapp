package com.finance.app.mapper;

import com.finance.app.dto.CategoryDto;
import com.finance.app.models.Category;

public class CategoryMapper {
    public static CategoryDto mapToCategoryDto(Category category) {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(category.getId())
                .type(category.getType())
                .name(category.getName())
                .build();
        return categoryDto;
    }
    public static Category mapToCategory(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .type(categoryDto.getType())
                .name(categoryDto.getName())
                .build();
    }
}
