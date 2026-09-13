package com.rohit.springboot_learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohit.springboot_learning.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
