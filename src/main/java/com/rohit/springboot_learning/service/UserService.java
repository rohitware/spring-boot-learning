package com.rohit.springboot_learning.service;

import org.springframework.stereotype.Service;

import com.rohit.springboot_learning.model.User;
import com.rohit.springboot_learning.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public String getUser() {
        return "Getting user...";
    }

    public String getUserById(int id) {
        return "User ID: " + id;
    }

    public String updateUser(int id, User user) {
        return "User " + id + " updated: " + user.getName() + ", Age: " + user.getAge();
    }

    public String deleteUser(int id) {
        return "User " + id + " deleted successfully";
    }
}
