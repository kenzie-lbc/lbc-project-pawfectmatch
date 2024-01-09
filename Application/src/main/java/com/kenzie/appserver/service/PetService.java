package com.kenzie.appserver.service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.kenzie.appserver.controller.model.PetCreateRequest;
import com.kenzie.appserver.controller.model.PetCreateResponse;
import com.kenzie.appserver.repositories.PetRepository;
import com.kenzie.appserver.repositories.enums.PetType;

// import com.kenzie.appserver.repositories.model.AdoptedPet;
import com.kenzie.appserver.repositories.model.Pet;

import com.kenzie.appserver.service.exceptions.InvalidPetException;
import com.kenzie.appserver.service.utils.UniqueIdGenerator;
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
    private final UniqueIdGenerator uniqueIdGenerator;


    // Constructor
    public PetService(PetRepository petRepository, Cloudinary cloudinary, UniqueIdGenerator uniqueIdGenerator) {
        this.petRepository = petRepository;
        this.cloudinary = cloudinary;
        this.uniqueIdGenerator = uniqueIdGenerator;
    }

    // Method to handle saving a new pet

    public Pet createPet(PetCreateRequest petCreateRequest) {
        if (petCreateRequest == null) {
            throw new InvalidPetException("Pet request cannot be null!");
        }

        PetType petType = petCreateRequest.getPetType();
        if (petType == null) {
            throw new InvalidPetException("Pet type cannot be null!");
        }
        // Call UniqueIdGenerator to create petId, then 'set'
        String petId = uniqueIdGenerator.generatePetId(petType);

        Pet pet = new Pet();
        pet.setPetId(petId);
        pet.setPetType(petType);
        pet.setName(petCreateRequest.getName());
        pet.setAge(petCreateRequest.getAge());
        pet.setImageUrl(petCreateRequest.getImageUrl());

        // Save the pet object using your repository
        return  petRepository.save(pet);
    }

    public Pet findByPetId(String petId) throws InvalidPetException {
        return petRepository.findById(petId)
                .orElseThrow(() -> new InvalidPetException("Pet not found!"));
    }

    // Method to find pets by type
    //if statement for isDeleted?
    public List<Pet> findByPetType(PetType petType) {
        return petRepository.findByPetType(petType);

    }

    // Other methods specific to certain pet types

    // Method to find dogs
    public List<Pet> getDogs() {
        return petRepository.findByPetType(DOG);
    }

    // Method to find cats
    public List<Pet> findCats() {
        return petRepository.findByPetType(CAT);
    }

    //TODO - ADD UPDATE PET
// Update Pet method
    public Pet updatePet(Pet pet) throws InvalidPetException {
        Pet existingPet = petRepository.findById(pet.getPetId())
                .orElseThrow(() -> new InvalidPetException("Pet not found!"));

        // Validate pet name
        if (!StringUtils.isEmpty(pet.getName())) {
            existingPet.setName(pet.getName());
        }

        // Validate pet age
        if (pet.getAge() > 0) {
            existingPet.setAge(pet.getAge());
        }

        return petRepository.save(existingPet);
    }

    public void deletePet(String petId) {
//        Pet pet1 = petRepository.findByPetId(petId);
//        if (pet1.isAdopted()) {
        //does adoptPet() add pet to Adopted Pet table?
        petRepository.deleteById(petId);
//        }
    }
//    public void softDeletePet(String id) {
//        Pet pet = findPetById(id);
//        pet.setAdopted(true);
//    }

    public PetCreateResponse convertToPetCreateResponse(Pet pet) {
        // Implement the conversion logic
        PetCreateResponse response = new PetCreateResponse();

        response.setPetId(pet.getPetId());
        response.setName(pet.getName());
        response.setAge(pet.getAge());
        response.setPetType(pet.getPetType());
        response.setImageUrl(pet.getImageUrl());

        return response;
    }

//    public List<Pet> findAllAdoptablePets() {
//        List<Pet> adoptablePets = new ArrayList<>();
//
//        Iterable<Pet> petIterator = petRepository.findAllPets();
//        for(Pet pet : petIterator) {
//            if (pet.isAdopted()) {
//                adoptablePets.add(pet);
//            }
//
//        }
//
//        return adoptablePets;
//    }

    //    public String handleImageUpload(MultipartFile image) throws IOException {
//        // Image uploading
//            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
//            String imageUrl = (String) uploadResult.get("url");
//
//        return imageUrl;
//    }
}
