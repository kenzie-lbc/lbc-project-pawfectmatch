package com.kenzie.appserver.controller;

import com.kenzie.appserver.repositories.enums.PetType;
import com.kenzie.appserver.repositories.model.Pet;
import com.kenzie.appserver.service.InvalidPetException;
import com.kenzie.appserver.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestParam("image") MultipartFile image, @RequestBody Pet pet) throws InvalidPetException {
        // Save image to S3 or file storage and get URL
//        String imageUrl = s3Service.uploadFile(image);

        // Set image URL on pet
//        pet.setImageUrl(imageUrl);

        Pet createdPet = petService.createPet(pet);
        return ResponseEntity.ok(createdPet);
    }


    @GetMapping("/{type}")
    public ResponseEntity<List<Pet>> getPetsByType(@PathVariable PetType petType) {
        List<Pet> pets = petService.findPetsByType(petType.toString());
        return ResponseEntity.ok(pets);
    }
}
