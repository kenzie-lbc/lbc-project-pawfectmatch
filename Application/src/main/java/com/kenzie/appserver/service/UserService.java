package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
//    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to create a new user
    public User createUser(User user) {

//        assignUserIdToUser(user);
//        encodeUserPassword(user);

        // Generate a user ID based on the user role before saving
//        String userId = UserIdGenerator.generateUserId(user.getRole());
//        user.setId(userId);
//
//        // Hash the password using BCrypt
//        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
//        user.setPassword(hashedPassword);

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

    public String getLoggedInUserId() {
        // Get the authenticated user details
        // Return the userId
        return null;
    }

    // Method to delete a user
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

//    private void assignUserIdToUser(User user) {
//        String userId = UserIdGenerator.generateUserId(user.getRole());
//        user.setId(userId);
//    }
//
//    private void encodeUserPassword(User user) {
//        String hashedPassword = encoder.encode(user.getPassword().toString());
//        user.setPassword(hashedPassword);
//    }
}
