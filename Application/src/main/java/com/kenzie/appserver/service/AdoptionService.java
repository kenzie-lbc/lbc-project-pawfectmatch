package com.kenzie.appserver.service.model;

import com.kenzie.appserver.repositories.model.AdoptionRecord;
import com.kenzie.appserver.repositories.model.Pet;
import com.kenzie.appserver.repositories.model.User;
import com.kenzie.appserver.service.PetNotFoundException;
import com.kenzie.appserver.service.PetService;
import com.kenzie.appserver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class AdoptionService {
    private PetService petService;
    private UserService userService;

public AdoptionService(PetService petService, UserService userService) {
    this.petService = petService;
    this.userService = userService;
}

    public Pet adoptPet(String id, String username, String dateOfAdoption) {
        // Your code here
        Pet pet = petService.findPetById(id);

        if (pet == null) {
            throw new PetNotFoundException("Pet not found");
        }

        AdoptionRecord adoptionRecord = new AdoptionRecord(
        adoptionRecord.setUsername(username),
        adoptionRecord.setPetId(id),
        adoptionRecord.setDateOfAdoption(dateOfAdoption));

        adoptionRecord.save(adoptionRecord);

        Pet adoptedPet = new Pet(
                pet.getAdoptionId(),
                pet.getId(),
                pet.getName(),
                pet.getType(),
                pet.getAge()

        );
        pet.setAdopted(true);


        return adoptedPet;
    }

    public List<Pet> findAllAdoptedPets() {
        List<Pet> adoptablePets = new ArrayList<>();

        Iterable<Pet> petIterator = petService.findAllPets();
        for(Pet pet : petIterator) {
            if (!pet.isAdopted()) {
                adoptablePets.add(pet);
            }

        }

        return adoptablePets;
    }
}
