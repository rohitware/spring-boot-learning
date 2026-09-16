package com.rohit.springboot_learning.repository;

import com.rohit.springboot_learning.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}