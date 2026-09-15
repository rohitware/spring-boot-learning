package com.rohit.springboot_learning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rohit.springboot_learning.model.Product;
import com.rohit.springboot_learning.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Long id, Product product) {
        // find existing product from database
        Product existingProduct = productRepository.findById(id).orElse(null);

        // if not found return null
        if (existingProduct == null) {
            return null;
        }
        // update fields
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());

        // save updated product back to database
        return productRepository.save(existingProduct);
    }

    public boolean deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            return false;
        }

        productRepository.deleteById(id);
        return true;
    }
}