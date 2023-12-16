package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ExampleCreateRequest;
import com.kenzie.appserver.controller.model.ExampleResponse;
import com.kenzie.appserver.repositories.model.Pet;
import com.kenzie.appserver.service.ExampleService;
import com.kenzie.appserver.service.PetService;
import com.kenzie.appserver.service.model.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        Pet createdPet = petService.createPet(pet);
        return ResponseEntity.ok(createdPet);
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<Pet>> getPetsByType(@PathVariable String type) {
        List<Pet> pets = petService.findPetsByType(type);
        return ResponseEntity.ok(pets);
    }
}
