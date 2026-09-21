package com.rohit.springboot_learning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rohit.springboot_learning.dto.ProductRequest;
import com.rohit.springboot_learning.dto.ProductResponse;
import com.rohit.springboot_learning.exception.ProductNotFoundException;
import com.rohit.springboot_learning.model.Product;
import com.rohit.springboot_learning.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();

        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        Product saveProduct = productRepository.save(product);

        return new ProductResponse(
                saveProduct.getId(),
                saveProduct.getName(),
                saveProduct.getPrice());
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + id));

        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice()))
                .toList();
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        // find existing product from database
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + id));

        // update fields
        existingProduct.setName(productRequest.getName());
        existingProduct.setPrice(productRequest.getPrice());

        // save updated product back to database
        Product updatedProduct = productRepository.save(existingProduct);
        return new ProductResponse(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getPrice());
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