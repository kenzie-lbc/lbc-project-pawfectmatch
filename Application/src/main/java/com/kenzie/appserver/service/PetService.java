package com.kenzie.appserver.service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.kenzie.appserver.controller.model.PetCreateRequest;
import com.kenzie.appserver.controller.model.PetCreateResponse;
import com.kenzie.appserver.repositories.PetRepository;
import com.kenzie.appserver.repositories.enums.PetType;

// import com.kenzie.appserver.repositories.model.AdoptedPet;
import com.kenzie.appserver.repositories.model.Pet;

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
        Pet pet = new Pet();
        PetType petType = petCreateRequest.getPetType();


        pet.setPetId(uniqueIdGenerator.generatePetId(petType));

        pet.setPetType(petType);
        pet.setName(petCreateRequest.getName());
        pet.setAge(petCreateRequest.getAge());
        pet.setImageUrl(petCreateRequest.getImageUrl());

        // Call UniqueIdGenerator to create petId, then 'set'

        // Save the pet object using your repository

        // Convert to DTO if necessary and return
        return  petRepository.save(pet);
    }


    public Pet findByPetId(String petId) throws InvalidPetException {
        return petRepository.findById(petId)
                .orElseThrow(() -> new InvalidPetException("Pet not found!"));
    }
    // TODO - FIX THIS
    // Method to find pets by name

    // Method to find pets by type
    //if statement for isDeleted?
    public List<Pet> findByPetType(PetType petType) {
        return petRepository.findByPetType(petType);

    }

    // Other methods specific to certain pet types

    //TODO - ADD NEW PET TO LIST
    // -- is this redundant since we have create pet?? negative
    // --> For favorites list - can remove if we decide to have favorites list as separate beast on its own
    public Pet addNewPet(Pet pet) {
        return pet;
    }


    // Method to find dogs
    public List<Pet> getDogs() {
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

    public void deletePet(String petId) {
        Pet pet1 = petRepository.findByPetId(petId);
//        if (pet1.isAdopted()) {
        //does adoptPet() add pet to Adopted Pet table?
        petRepository.deleteById(petId);
//        }
    }
//    public void softDeletePet(String id) {
//        Pet pet = findPetById(id);
//        pet.setAdopted(true);
//    }


//    public String handleImageUpload(MultipartFile image) throws IOException {
//        // Image uploading
//            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
//            String imageUrl = (String) uploadResult.get("url");
//
//        return imageUrl;
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
}
