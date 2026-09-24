package com.rohit.springboot_learning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rohit.springboot_learning.dto.ProductRequest;
import com.rohit.springboot_learning.dto.ProductResponse;
import com.rohit.springboot_learning.exception.CategoryNotFoundException;
import com.rohit.springboot_learning.exception.ProductNotFoundException;
import com.rohit.springboot_learning.model.Product;
import com.rohit.springboot_learning.repository.CategoryRepository;
import com.rohit.springboot_learning.repository.ProductRepository;
import com.rohit.springboot_learning.model.Category;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();

        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        Category category = categoryRepository
                .findById(productRequest.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + productRequest.getCategoryId()));

        product.setCategory(category);

        Product saveProduct = productRepository.save(product);

        return new ProductResponse(
                saveProduct.getId(),
                saveProduct.getName(),
                saveProduct.getPrice(),
                saveProduct.getCategory().getId(),
                saveProduct.getCategory().getName());
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + id));

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getCategory().getId(),
                        product.getCategory().getName()))
                .toList();
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + id));

        existingProduct.setName(productRequest.getName());
        existingProduct.setPrice(productRequest.getPrice());

        Category category = categoryRepository
                .findById(productRequest.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + productRequest.getCategoryId()));

        existingProduct.setCategory(category);

        Product updatedProduct = productRepository.save(existingProduct);

        return new ProductResponse(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getPrice(),
                updatedProduct.getCategory().getId(),
                updatedProduct.getCategory().getName());
    }

    public boolean deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(
                    "Product not found with id: " + id);
        }

        productRepository.deleteById(id);
        return true;
    }
}