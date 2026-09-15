package com.rohit.springboot_learning.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.rohit.springboot_learning.model.Product;
import com.rohit.springboot_learning.service.ProductService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // constructor injection - Spring injects ProductService automatically
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /products - get all products from database
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET /products/{id} - get product by id from database
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {

        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product); // 200
    }

    // POST /products - save product to database
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product saveProduct = productService.createProduct(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saveProduct);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updateProduct = productService.updateProduct(id, product);
        if (updateProduct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);

        if (!deleted) {
            return ResponseEntity.notFound().build(); // 404
        }

        return ResponseEntity.ok("Product " + id + " deleted successfully"); // 200
    }
}
