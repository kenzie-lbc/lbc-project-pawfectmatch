package com.kenzie.appserver.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.kenzie.appserver.controller.model.PetCreateRequest;
import com.kenzie.appserver.controller.model.PetCreateResponse;
import com.kenzie.appserver.repositories.PetRepository;
import com.kenzie.appserver.repositories.enums.PetType;
import com.kenzie.appserver.repositories.model.Pet;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.kenzie.appserver.repositories.enums.PetType.CAT;
import static com.kenzie.appserver.repositories.enums.PetType.DOG;

@Service
public class PetService {
    private final PetRepository petRepository;
    private final Cloudinary cloudinary;


    // Constructor
    public PetService(PetRepository petRepository, Cloudinary cloudinary) {
        this.petRepository = petRepository;
        this.cloudinary = cloudinary;
    }

    // Method to handle saving a new pet
//    public Pet createPet(Pet pet, MultipartFile image) throws InvalidPetException, IOException {
//        try {
//            if (StringUtils.isEmpty(pet.getName())) {
//                throw new InvalidPetException("Pet name is required");
//            }
//            if (pet.getAge() <= 0) {
//                throw new InvalidPetException("Pet age must be greater than 0");
//            }
//            if (pet.getPetType() == null) {
//                throw new InvalidPetException("Pet type is required");
//            }
//
//            // Set PetID using UniqueIdGenerator
//            String petId = UniqueIdGenerator.generatePetId(pet.getPetType());
//            pet.setPetId(petId);
//
//
//        // Set image
////        pet.setImageUrl(imageUrlGenerator());
////
////        // Get the userId of the user creating the pet
////        String userId = getLoggedInUserId();
////
////        // Set the adoptionId to the userId
////        pet.setAdoptionId(userId);
//
//            // Save pet using repository
//            pet = petRepository.save(pet);
//        } catch (InvalidPetException e) {
//            throw e;
//        }
//        // Return saved pet
//        return pet;
//    }
    public Pet createPet(PetCreateRequest petCreateRequest) {
        Pet pet = new Pet();
        pet.setName(petCreateRequest.getName());
        pet.setAge(petCreateRequest.getAge());
        pet.setImageUrl(petCreateRequest.getImageUrl());

        // Save the pet object using your repository
        Pet savedPet = petRepository.save(pet);

        // Convert to DTO if necessary and return
        return savedPet;
    }
    public List<Pet> findAllPets() {
        return (List<Pet>) petRepository.findAll();
    }

    public Pet findByPetId(String petId) throws InvalidPetException {
        return petRepository.findById(petId)
                .orElseThrow(() -> new InvalidPetException("Pet not found!"));
    }
    // TODO - FIX THIS
    // Method to find pets by name

    // Search for full pet profile
//    public List<Pet> findAllByPetId(String petId) {
//        return petRepository.findPetsByPetId(petId);
//    }

    // Method to find pets by type
    public List<Pet> findByPetType(PetType petType) {
        return petRepository.findByPetType(petType);
    }

    // Other methods specific to certain pet types

    // Method to find dogs
    public List<Pet> findDogs() {
        return petRepository.findByPetType(DOG);
    }

    // Method to find cats
    public List<Pet> findCats() {
        return petRepository.findByPetType(CAT);
    }

    //TODO - ADD UPDATE PET

    public Pet updatePet(Pet pet, MultipartFile file) throws InvalidPetException, IOException {
        Pet existingPet = petRepository.findById(pet.getPetId()).orElseThrow(() -> new InvalidPetException("Pet not found!"));

        // validate pet object
        if (StringUtils.isEmpty(pet.getName())) {
            throw new InvalidPetException("Pet name is required");
        }

        // upload file and check for success
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String fileUrl = (String) uploadResult.get("url");

        boolean isUploaded = !fileUrl.isEmpty();


        if (isUploaded) {
            // Add a condition to check whether the upload was successful and act on it
            existingPet.setImageUrl(pet.getImageUrl());
            existingPet.setName(pet.getName());
            existingPet.setAge(pet.getAge());
            existingPet.setPetType(pet.getPetType());

            // update any additional fields...

            // save the pet and return
            existingPet = petRepository.save(existingPet);
            return existingPet;
        } else {
            throw new IOException("File failed to upload!");
        }
    }

//    public String handleImageUpload(MultipartFile image) throws IOException {
//        // Image uploading
//            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
//            String imageUrl = (String) uploadResult.get("url");
//
//        return imageUrl;
//    }
    public PetCreateResponse
    convertToPetCreateResponse(Pet pet) {
        // Implement the conversion logic
            PetCreateResponse response = new PetCreateResponse();

            response.setPetId(pet.getPetId());
            response.setName(pet.getName());
            response.setAge(pet.getAge());
            response.setPetType(pet.getPetType());
            response.setImageUrl(pet.getImageUrl());

            return response;
        }
    }
