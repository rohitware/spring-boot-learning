package com.rohit.springboot_learning.service;

import org.springframework.stereotype.Service;

import com.rohit.springboot_learning.model.User;

@Service
public class UserService {

    public String getUser() {
        return "Getting user...";
    }

    public String getUserById(int id) {
        return "User ID: " + id;
    }

    public String createUser(User user) {
        return "User created: " + user.getAge() + ", Age: " + user.getAge();
    }

    public String updateUser(int id, User user) {
        return "User " + id + " updated: " + user.getName() + ", Age: " + user.getAge();
    }

    public String deleteUser(int id) {
        return "User " + id + " deleted successfully";
    }
}
