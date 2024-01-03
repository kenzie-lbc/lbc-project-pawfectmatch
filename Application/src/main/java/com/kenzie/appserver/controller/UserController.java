package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.AuthenticationRequest;
import com.kenzie.appserver.repositories.model.User;
import com.kenzie.appserver.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        return ResponseEntity.ok(user);

    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{uniqueId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String uniqueId) {
        userService.deleteUser(uniqueId);
        return ResponseEntity.noContent().build();
    }
}
