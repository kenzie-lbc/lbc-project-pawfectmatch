package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.PetRepository;
import com.kenzie.appserver.repositories.enums.PetType;
import com.kenzie.appserver.repositories.model.Pet;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kenzie.appserver.repositories.enums.PetType.CAT;
import static com.kenzie.appserver.repositories.enums.PetType.DOG;

@Service
public class PetService {

    private final PetRepository petRepository;

    // Constructor
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // Method to handle saving a new pet
    public Pet createPet(Pet pet) throws InvalidPetException {
        if (StringUtils.isEmpty(pet.getName())) {
            throw new InvalidPetException("Pet name is required");
        }
        if (pet.getAge() <= 0) {
            throw new InvalidPetException("Pet age must be greater than 0");
        }
        // Validate other fields...
        // Set PetID using UniqueIdGenerator
        String petId = UniqueIdGenerator.generatePetId(pet.getPetType());
        pet.setPetId(petId);

//        // Set image
//        pet.setImageUrl(imageUrl);
//
//        // Get the userId of the user creating the pet
//        String userId = getLoggedInUserId();
//
//        // Set the adoptionId to the userId
//        pet.setAdoptionId(userId);

        // Save pet using repository
        petRepository.save(pet);

        // Return saved pet
        return pet;
    }

    public List<Pet> findAllPets() {
        return (List<Pet>) petRepository.findAll();
    }

    // TODO - FIX THIS
    public List<Pet> findByPetId(String petId) {
        return petRepository.findByPetId(petId);
    }

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

    //TODO - ADD NEW PET TO LIST for necessary sibling/friend pairing
    public Pet addNewPet(Pet pet) {
        return pet;
    }
}
