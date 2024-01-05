package com.kenzie.appserver.controller;

import com.cloudinary.utils.ObjectUtils;
import com.kenzie.appserver.controller.model.PetCreateRequest;
import com.kenzie.appserver.controller.model.PetCreateResponse;
import com.kenzie.appserver.repositories.model.Pet;

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
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/Pet")
public class PetController {

    private final PetService petService;


    @Autowired
    private Cloudinary cloudinary;


    PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping(consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PetCreateResponse> createPet(@RequestPart("petCreateRequest") PetCreateRequest petCreateRequest,
                                                       @RequestPart("image") MultipartFile image) {
        if (StringUtils.isEmpty(petCreateRequest.getName())) {
            throw new InvalidPetException("Pet name is required");
        }
        if (petCreateRequest.getAge() <= 0) {
            throw new InvalidPetException("Pet age must be greater than 0");
        }
        try {
            // Upload image
            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = uploadResult.get("url").toString();

            // Create pet\
            Pet pet = new Pet();
            pet.setName(petCreateRequest.getName());
            pet.setPetType(petCreateRequest.getPetType());
            pet.setAge(petCreateRequest.getAge());
            pet.setImageUrl(imageUrl);


            Pet createdPet = petService.createPet(pet, image);

            PetCreateResponse petResponse = new PetCreateResponse();
            petResponse.setPetId(createdPet.getPetId());
            petResponse.setName(createdPet.getName());
            petResponse.setPetType(createdPet.getPetType());
            petResponse.setAge(createdPet.getAge());
            petResponse.setImageUrl(createdPet.getImageUrl());

            return new ResponseEntity<>(petResponse, HttpStatus.CREATED);
        } catch (InvalidPetException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            // Log the exception and return an appropriate response
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a Pet by ID
    @GetMapping("/{petId}")
    public ResponseEntity<Pet> getPetById(@PathVariable String petId) {
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

//    @GetMapping("/{type}")
//    public ResponseEntity<List<Pet>> getPetsByType(@PathVariable PetType petType) {
//        List<Pet> pets = petService.findPetsByType(petType);
//        return ResponseEntity.ok(pets);
//    }

}
