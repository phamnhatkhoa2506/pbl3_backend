package com.pbl3.supermarket.controller;

import com.pbl3.supermarket.dto.request.CategoryCreationRequest;
import com.pbl3.supermarket.dto.response.ApiResponse;
import com.pbl3.supermarket.dto.response.CategoryResponse;
import com.pbl3.supermarket.entity.Category;
import com.pbl3.supermarket.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ApiResponse<Category> createCategory(@RequestBody CategoryCreationRequest request) {
        return ApiResponse.<Category>builder()
                .message("Created a category")
                .result(categoryService.CreateCategory(request))
                .build();
    }
    
    @GetMapping("/all")
    public ApiResponse<List<CategoryResponse>> getAllCategories() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .message("All categories")
                .result(categoryService.GetAllCategories())
                .build();
    }
}
