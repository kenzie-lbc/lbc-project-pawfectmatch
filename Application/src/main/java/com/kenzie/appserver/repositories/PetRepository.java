package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.ExampleRecord;
import com.kenzie.appserver.repositories.model.Pet;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface PetRepository extends CrudRepository<ExampleRecord, String> {
    // Methods to handle CRUD operations
    Pet save(Pet pet);
    List<Pet> findByType(String type);
    Pet findPetById(String id);
    List<Pet>findAllPets();

    // ... other methods
}
