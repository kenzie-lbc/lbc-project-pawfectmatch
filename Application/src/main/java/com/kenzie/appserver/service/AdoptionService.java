package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.AdoptionRepository;
import com.kenzie.appserver.repositories.model.AdoptedPet;
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
    private AdoptionService adoptionService;
    private UserService userService;
    private PetService petService;
    private AdoptionRepository adoptionRepository;

public AdoptionService(AdoptionService adoptionService, UserService userService, PetService petService) {
    this.adoptionService = adoptionService;
    this.userService = userService;
    this.petService = petService;
}

    public AdoptedPet adoptPet(String id, String username, String dateOfAdoption) {
        // Your code here
        //AdoptedPet pet = adoptionService.findPetById(id);
        Pet pet = petService.findPetById(id);

        if (pet == null) {
            throw new PetNotFoundException("Pet not found");
        }
        if (!pet.isAdopted()) {
            throw new PetAlreadyAdoptedException("Pet already adopted");
        }

        AdoptionRecord adoptionRecord = new AdoptionRecord(
        adoptionRecord.setUsername(username),
        adoptionRecord.setPetId(id),
        adoptionRecord.setDateOfAdoption(dateOfAdoption));

        adoptionRepository.saveRecord(adoptionRecord);

        Pet adoptedPet = new Pet(
                pet.getAdoptionId(),
                pet.getId(),
                pet.getName(),
                pet.getType(),
                pet.getAge()

        );

        adoptedPet.setAdopted(true);

        petService.updatePet(adoptedPet);

        AdoptedPet adoptedPet1 = new AdoptedPet(
                adoptionRecord.getDateOfAdoption(),
                adoptionRecord.getUsername(),
                adoptionRecord.getPetId()
        );

        return adoptedPet1;
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