package com.finance.app.service;

import com.finance.app.dto.CategoryDto;
import com.finance.app.models.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAllCategories();

    CategoryDto findCategoryById(int categoryId);

    CategoryDto saveCategory(CategoryDto categoryDto);
}
