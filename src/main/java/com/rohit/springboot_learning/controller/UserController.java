package com.rohit.springboot_learning.controller;

import com.rohit.springboot_learning.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String displayUser() {
        userService.getUser();
        return "User fetched successfully";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id) {
        return "User ID" + id;
    }
}