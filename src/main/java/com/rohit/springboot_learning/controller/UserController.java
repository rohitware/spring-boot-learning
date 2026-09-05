package com.rohit.springboot_learning.controller;

import com.rohit.springboot_learning.service.UserService;
import com.rohit.springboot_learning.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable int id,
            @RequestBody User user) {

        String response = "User " + id + " updated: "
                + user.getName() + ", Age: " + user.getAge();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {

        return ResponseEntity.ok("User " + id + " deleted successfully");
    }

}