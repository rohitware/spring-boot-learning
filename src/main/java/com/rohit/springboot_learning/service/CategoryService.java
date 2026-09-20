package com.rohit.springboot_learning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rohit.springboot_learning.dto.CategoryRequest;
import com.rohit.springboot_learning.dto.CategoryResponse;
import com.rohit.springboot_learning.exception.CategoryNotFoundException;
import com.rohit.springboot_learning.model.Category;
import com.rohit.springboot_learning.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // public Category createCategory(Category category) {
    // return categoryRepository.save(category);
    // }
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

        Category category = new Category();

        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        // Save entity to database
        Category savedCategory = categoryRepository.save(category);
        // Entity → Response DTO
        return new CategoryResponse(
                savedCategory.getId(),
                savedCategory.getName(),
                savedCategory.getDescription());

    }

    public CategoryResponse getCategoryById(Long id) {

        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription());
    }

    public List<CategoryResponse> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category -> new CategoryResponse(
                        category.getId(),
                        category.getName(),
                        category.getDescription()))
                .toList();
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        // find existing Category from database

        Category existingCategory = categoryRepository
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        // update fields
        existingCategory.setName(categoryRequest.getName());
        existingCategory.setDescription(categoryRequest.getDescription());

        // save updated Category back to database
        Category updatedCategory = categoryRepository.save(existingCategory);
        // Entity → Response DTO
        return new CategoryResponse(
                updatedCategory.getId(),
                updatedCategory.getName(),
                updatedCategory.getDescription());
    }

    public void deleteCategory(Long id) {

        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category not found");
        }

        categoryRepository.deleteById(id);
    }
}