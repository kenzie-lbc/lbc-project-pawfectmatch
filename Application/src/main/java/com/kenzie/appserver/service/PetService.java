package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.PetRepository;
import com.kenzie.appserver.repositories.model.AdoptedPet;
import com.kenzie.appserver.repositories.model.Pet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void updatePet(Pet pet) {

        if (petRepository.existsById(pet.getId())) {
            Pet pet1 = new Pet(
                    pet.getAdoptionId(),
                    pet.getId(),
                    pet.getName(),
                    pet.getType(),
                    pet.getAge()
                    );
        }
    }

    // Method to find pets by type
    //if statement for isDeleted?
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

    public List<Pet> findAllPets() {
        List<Pet> pets = new ArrayList<>();

        Iterable<Pet> petIterator = petRepository.findAllPets();
        for(Pet pet : petIterator) {
            pets.add(new Pet(pet.getId(),
                    pet.getName(),
                    pet.getAdoptionId(),
                    pet.getType(),
                    pet.getAge()));

        }

        return pets;
    }
    public List<Pet> findAllAdoptablePets() {
        List<Pet> adoptablePets = new ArrayList<>();

        Iterable<Pet> petIterator = petRepository.findAllPets();
        for(Pet pet : petIterator) {
            if (pet.isAdopted()) {
                adoptablePets.add(pet);
            }

        }

        return adoptablePets;
    }

    public Pet findPetById(String id) {
        List<Pet> pets = petRepository.findAllPets();
        for(Pet pet: pets) {
            if (pet.getId() == id)
                return pet;
        }
        return null;
    }
    public void deletePet(String id) {
        Pet pet1 = petRepository.findPetById(id);
        if (pet1.isAdopted()) {
            //does adoptPet() add pet to Adopted Pet table?
            petRepository.deleteById(id);
        }
    }
    public void softDeletePet(String id) {
        Pet pet = findPetById(id);
        pet.setAdopted(true);
    }



}
