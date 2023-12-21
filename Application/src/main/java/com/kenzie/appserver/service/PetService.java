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
//        pet.setId(UniqueIdGenerator.generatePetId(pet.getType()),

//        // Set image
//        pet.setImageUrl(imageUrl);
//
//        // Get the userId of the user creating the pet
//        String userId = getLoggedInUserId();
//
//        // Set the adoptionId to the userId
//        pet.setAdoptionId(userId);

        // Save pet using repository
//        petRepository.save(pet).toString())
        // Return saved pet
        ;return pet;
    }

    // Method to find pets by type
    public List<Pet> findPetsByType(PetType petType) {
        return petRepository.findByType(petType);
    }

    // Other methods specific to certain pet types

    // Method to find dogs
    public List<Pet> findDogs() {
        return petRepository.findByType(DOG);
    }

    // Method to find cats
    public List<Pet> findCats() {
        return petRepository.findByType(CAT);
    }
}
