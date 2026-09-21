package com.rohit.springboot_learning.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.rohit.springboot_learning.dto.ProductRequest;
import com.rohit.springboot_learning.dto.ProductResponse;
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
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET /products/{id} - get product by id from database
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {

        ProductResponse productResponse = productService.getProductById(id);
        return ResponseEntity.ok(productResponse); // 200
    }

    // POST /products - save product to database
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse saveProduct = productService.createProduct(productRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saveProduct);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
            @RequestBody ProductRequest productRequest) {
        ProductResponse updatedProduct = productService.updateProduct(id, productRequest);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product " + id + " deleted successfully"); // 200
    }
}
