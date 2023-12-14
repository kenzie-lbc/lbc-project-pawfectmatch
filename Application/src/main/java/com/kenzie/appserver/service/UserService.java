package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to create a new user
    public User createUser(User user) {
        // Generate a unique ID based on the user role before saving
        String uniqueId = UniqueIdGenerator.generateUserId(user.getRole());
        user.setId(uniqueId);

        // Save user using repository
        return userRepository.save(user);
    }

    // Method to find a user by username
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Method to update a user
    public User updateUser(User user) {
        // Only allow specific modifications based on user role
        // Implement your logic here based on the user's role
        // For example, if user.getRole() == "shelter", only allow modifications to pets they created
        // If user.getRole() == "foster", only allow modifications to pets they are fostering
        // If user.getRole() == "adopter", allow modifications to their favorite list

        return userRepository.save(user);
    }

    // Method to delete a user
    public void deleteUser(String uniqueId) {
        userRepository.deleteById(uniqueId);
    }
}
