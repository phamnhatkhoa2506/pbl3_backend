package com.pbl3.supermarket.service;

import com.pbl3.supermarket.dto.request.CategoryCreationRequest;
import com.pbl3.supermarket.dto.response.CategoryResponse;
import com.pbl3.supermarket.entity.Category;
import com.pbl3.supermarket.exception.AppException;
import com.pbl3.supermarket.exception.ErrorCode;
import com.pbl3.supermarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category CreateCategory(CategoryCreationRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);
    }

    public List<CategoryResponse> GetAllCategories() {
        List<CategoryResponse> categoriesResponse = new ArrayList<>();
        categoryRepository.findAll().forEach(category -> {
            categoriesResponse.add(
                    CategoryResponse.builder()
                            .id(category.getId())
                            .description(category.getDescription())
                            .name(category.getName())
                            .build()
            );
        });
        return categoriesResponse;
    }

    public CategoryResponse GetCategoryById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_ID_NOTFOUND));
        return CategoryResponse.builder()
                .id(category.getId())
                .description(category.getDescription())
                .name(category.getName())
                .build();
    }
}
