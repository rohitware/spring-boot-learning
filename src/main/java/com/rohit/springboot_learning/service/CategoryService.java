package com.rohit.springboot_learning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rohit.springboot_learning.model.Category;
import com.rohit.springboot_learning.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(Long id, Category category) {
        // find existing Category from database

        Category existingCategory = categoryRepository.findById(id).orElse(null);

        // if not found return null
        if (existingCategory == null) {
            return null;
        }
        // update fields
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());

        // save updated Category back to database
        return categoryRepository.save(existingCategory);
    }
}