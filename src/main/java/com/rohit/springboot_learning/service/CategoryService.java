package com.rohit.springboot_learning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rohit.springboot_learning.exception.CategoryNotFoundException;
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
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(Long id, Category category) {
        // find existing Category from database

        Category existingCategory = categoryRepository.findById(id).orElse(null);

        // if not found return null
        if (existingCategory == null) {
            throw new CategoryNotFoundException("Category not found");
        }
        // update fields
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());

        // save updated Category back to database
        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id) {

        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category not found");
        }

        categoryRepository.deleteById(id);
    }
}