package com.rohit.springboot_learning.controller;

import com.rohit.springboot_learning.service.UserService;
import com.rohit.springboot_learning.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetMapping("/search")
    public String searchUser(@RequestParam String name) {
        return "Searching user: " + name;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {

        String response = "User created: " + user.getName()
                + ", Age: " + user.getAge();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}