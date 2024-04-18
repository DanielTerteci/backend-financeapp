package com.finance.app.service.impl;

import com.finance.app.dto.CategoryDto;
import com.finance.app.mapper.CategoryMapper;
import com.finance.app.models.Category;
import com.finance.app.repository.CategoryRepository;
import com.finance.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.finance.app.mapper.CategoryMapper.mapToCategoryDto;


@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<CategoryDto> findAllCategories() {
        List<Category> category = categoryRepository.findAll();
        return category.stream().map((categories1 -> mapToCategoryDto(categories1))).collect(Collectors.toList());
    }
    @Override
    public CategoryDto findCategoryById(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null) {
            return mapToCategoryDto(category);
        }
        return null;
    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.mapToCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(savedCategory);
    }
}
