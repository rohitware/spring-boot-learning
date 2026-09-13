package com.rohit.springboot_learning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.rohit.springboot_learning.model.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<Product> getAllProducts() {
        return List.of(
                new Product(1L, "Laptop", 50000.00),
                new Product(2L, "Phone", 20000.00),
                new Product(3L, "Tablet", 30000.00));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable Long id) {

        if (id == 1L) {
            return ResponseEntity.ok("Product found with id: " + id);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return product;
    }

    public void Product() {

    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam(required = false, defaultValue = "all") String name) {
        return "Searching for product " + name;
    }

}
