package com.rohit.springboot_learning.repository;

import com.rohit.springboot_learning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}