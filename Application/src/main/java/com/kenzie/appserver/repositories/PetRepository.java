package com.kenzie.appserver.repositories;


import com.kenzie.appserver.service.PetService;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.kenzie.appserver.repositories.enums.PetType;
import com.kenzie.appserver.repositories.model.Pet;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

import org.socialsignin.spring.data.dynamodb.repository.Query;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableScan

@Repository
public interface PetRepository extends CrudRepository<Pet, String> {

    // Methods to handle CRUD operations
    Pet findByPetId(String petId);

    List<Pet> findByPetType(PetType petType);

    // ... other methods
    List<Pet> findByName(String name);
    List<Pet> findByAge(int age);
//    List<Pet> findByBreed(String breed);

//    List<Pet> findPetsByPetId(String petId);

}
