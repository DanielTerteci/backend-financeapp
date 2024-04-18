package com.finance.app.controller;

import com.finance.app.dto.CategoryDto;
import com.finance.app.dto.ExpenseDto;
import com.finance.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<CategoryDto> category = categoryService.findAllCategories();
        System.out.println("Categories retrieved: " + category);
        return ResponseEntity.ok(category);
    }

    @PostMapping("/save")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto) {
    CategoryDto savedCategory = categoryService.saveCategory(categoryDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
}

}