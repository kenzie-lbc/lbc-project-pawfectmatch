package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.PetCreateRequest;
import com.kenzie.appserver.controller.model.PetCreateResponse;
import com.kenzie.appserver.repositories.enums.PetType;
import com.kenzie.appserver.repositories.model.Pet;

import com.kenzie.appserver.service.InvalidPetException;
import com.kenzie.appserver.service.PetService;
import com.kenzie.appserver.service.S3Service;

import com.amazonaws.services.s3.model.S3Object;

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

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;
    private final S3Service s3Service;

    @Autowired
    PetController(PetService petService, S3Service s3Service) {
        this.petService = petService;
        this.s3Service = s3Service;
    }

    @PostMapping("/pets")
    public ResponseEntity<PetCreateResponse> createPet(@RequestBody PetCreateRequest petCreateRequest,
                                                 @RequestParam("image") MultipartFile image
    ) throws InvalidPetException, IOException {
        Pet pet = new Pet();
        pet.setName(petCreateRequest.getName());
        pet.setPetType(petCreateRequest.getPetType());
        pet.setAge(petCreateRequest.getAge());
        String imageUrl = s3Service.uploadFile(image);
        pet.setImageUrl(imageUrl);
        Pet createdPet = petService.createPet(pet);
        PetCreateResponse petResponse = new PetCreateResponse();
        petResponse.setId(createdPet.getId());
        petResponse.setName(createdPet.getName());
        petResponse.setPetType(createdPet.getPetType());
        petResponse.setAge(createdPet.getAge());
        petResponse.setImageUrl(createdPet.getImageUrl());
        return new ResponseEntity<>(petResponse, HttpStatus.CREATED);
    }


    @GetMapping("/{type}")
    public ResponseEntity<List<Pet>> getPetsByType(@PathVariable PetType petType) {
        List<Pet> pets = petService.findPetsByType(petType);
        return ResponseEntity.ok(pets);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String filename = s3Service.uploadFile(file);
        return ResponseEntity.ok(filename);
    }

    @GetMapping("/files/{filename}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
        S3Object s3Object = s3Service.downloadFile(filename);
        InputStreamResource resource = new InputStreamResource(s3Object.getObjectContent());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(s3Object.getObjectMetadata().getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }
}
