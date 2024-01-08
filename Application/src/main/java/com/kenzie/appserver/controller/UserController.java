package com.kenzie.appserver.controller;

//import com.kenzie.appserver.controller.model.AuthenticationRequest;
import com.kenzie.appserver.repositories.model.Pet;
import com.kenzie.appserver.repositories.model.User;
import com.kenzie.appserver.service.PetService;
import com.kenzie.appserver.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PetService petService;

    public UserController(UserService userService, PetService petService) {
        this.userService = userService;
        this.petService = petService;
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

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable String id, @RequestBody Pet updatedPet) {
        updatedPet.setPetId(id);

        updatedPet.setPetId(id);
        Pet updatedPetResult = petService.updatePet(updatedPet);
        return ResponseEntity.ok(updatedPetResult);
    }

    @DeleteMapping("/{uniqueId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String uniqueId) {
        userService.deleteUser(uniqueId);
        return ResponseEntity.noContent().build();
    }
}
