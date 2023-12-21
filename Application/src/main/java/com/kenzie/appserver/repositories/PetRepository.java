package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.enums.PetType;
import com.kenzie.appserver.repositories.model.Pet;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface PetRepository extends CrudRepository<Pet, String> {
    // Methods to handle CRUD operations
    Pet save(Pet pet);
    List<Pet> findByType(PetType petType);

    // ... other methods
}
