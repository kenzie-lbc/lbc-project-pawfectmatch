package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.PetRepository;
import com.kenzie.appserver.repositories.model.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;

    // Constructor
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // Method to handle saving a new pet
    public Pet createPet(Pet pet) {
        // Set PetID using UniqueIdGenerator
        pet.setId(UniqueIdGenerator.generatePetId(pet.getType()));

        // Get the userId of the user creating the pet
//        String userId = getLoggedInUserId();

        // Set the adoptionId to the userId
//        pet.setAdoptionId(userId);
        // Save pet using repository
        petRepository.save(pet);

        // Return saved pet
        return pet;
    }

    // Method to find pets by type
    public List<Pet> findPetsByType(String type) {
        return petRepository.findByType(type);
    }

    // Other methods specific to certain pet types

    // Method to find dogs
    public List<Pet> findDogs() {
        return petRepository.findByType("Dog");
    }

    // Method to find cats
    public List<Pet> findCats() {
        return petRepository.findByType("Cat");
    }
}
