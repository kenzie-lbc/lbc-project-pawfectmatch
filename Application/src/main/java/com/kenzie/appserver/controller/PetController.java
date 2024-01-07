package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.PetCreateRequest;
import com.kenzie.appserver.controller.model.PetCreateResponse;
import com.kenzie.appserver.repositories.model.Pet;
import com.kenzie.appserver.repositories.enums.PetType;
import com.kenzie.appserver.repositories.PetRepository;
import com.kenzie.appserver.service.InvalidPetException;
import com.kenzie.appserver.service.PetService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.io.IOException;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;


@RestController
@RequestMapping("/Pet")
public class PetController {

    private final PetService petService;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private Cloudinary cloudinary;

    public PetController(PetService petService) {
        this.petService = petService;

    }

    @PostMapping
    public ResponseEntity<PetCreateResponse> createPet(@RequestBody PetCreateRequest petCreateRequest) {
        if (StringUtils.isEmpty(petCreateRequest.getName())) {
            throw new InvalidPetException("Pet name is required");
        }
        if (petCreateRequest.getAge() <= 0) {
            throw new InvalidPetException("Pet age must be greater than 0");
        }

        try {
            // Save and convert the pet object
            Pet pet = petService.createPet(petCreateRequest);
            PetCreateResponse petResponse = petService.convertToPetCreateResponse(pet);
            return new ResponseEntity<>(petResponse, HttpStatus.CREATED);
        } catch (InvalidPetException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/Pet")
    public ResponseEntity<List<Pet>> getAllPets() {
        // Cast to List as findAll returns Iterable
        List<Pet> pets = petRepository.findAll();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    // Get a Pet by ID
    @GetMapping("/petId/{petId}")
    public ResponseEntity<Pet> getByPetId(@PathVariable String petId) {
        try {
            Pet pet = petService.findByPetId(petId);
            if (pet != null) {
                return new ResponseEntity<>(pet, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // log the error message and return a general error message to the client
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/petType/{petType}")
    public ResponseEntity<List<Pet>> getByPetType(@PathVariable PetType petType) {
        List<Pet> pets = petService.findByPetType(petType);
        return ResponseEntity.ok(pets);
    }


    @DeleteMapping("/petId/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable("petId") String petId) {
        petService.deletePet(petId);
        return ResponseEntity.status(204).build();
    }

}
