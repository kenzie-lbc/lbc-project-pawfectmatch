package com.kenzie.appserver.service;


import com.cloudinary.Cloudinary;
import com.kenzie.appserver.controller.PetController;
import com.kenzie.appserver.controller.UserController;
import com.kenzie.appserver.controller.model.PetCreateRequest;
import com.kenzie.appserver.controller.model.PetCreateResponse;
import com.kenzie.appserver.repositories.PetRepository;
import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.enums.PetType;
import com.kenzie.appserver.repositories.model.Pet;

import com.kenzie.appserver.service.exceptions.InvalidPetException;
import com.kenzie.appserver.service.utils.UniqueIdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Optional;

import static com.kenzie.appserver.repositories.enums.PetType.DOG;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class PetServiceTest {
    @Mock
    private PetRepository petRepository;
    @InjectMocks
    private PetService petService;

    @Mock
    private UniqueIdGenerator uniqueIdGenerator;

    @Mock
    private Cloudinary cloudinary;

    @Mock
    private PetController petController;


    @BeforeEach
    void setup() {
        petRepository = mock(PetRepository.class);

        // Mock the uniqueIdGenerator
        uniqueIdGenerator = mock(UniqueIdGenerator.class);
        // Define a behavior: when generatePetId() is called, return a "static" petId"
        when(uniqueIdGenerator.generatePetId(any(PetType.class))).thenReturn("PETID123");

        petService = new PetService(petRepository, cloudinary, uniqueIdGenerator);
    }

    /**
     * ------------------------------------------------------------------------
     * PET SERVICE UNIT TESTING
     * ------------------------------------------------------------------------
     **/

    //happy case create new dog
    @Test
    void createNewDogTest_validData() {
        //given
        String name = "Buddy";
        PetType petType = DOG;
        int age = 3;
        String imageUrl = "https://example.com/dog.jpg";

        Pet expectedPet = new Pet();
        expectedPet.setName(name);
        expectedPet.setPetType(DOG);
        expectedPet.setAge(age);
        expectedPet.setImageUrl(imageUrl);

        PetCreateRequest petCreateRequest = new PetCreateRequest();
        petCreateRequest.setName(name);
        petCreateRequest.setPetType(petType);
        petCreateRequest.setAge(age);
        petCreateRequest.setImageUrl(imageUrl);

        // setup save method to return your expectedPet object
        when(petRepository.save(any(Pet.class))).thenReturn(expectedPet);
        //when
        Pet createdPet = petService.createPet(petCreateRequest);
        //then
        assertThat(createdPet).isNotNull();
        assertThat(createdPet.getName()).isEqualTo(expectedPet.getName());
        assertThat(createdPet.getAge()).isEqualTo(expectedPet.getAge());
        assertThat(createdPet.getPetType()).isEqualTo(petType);
        assertThat(createdPet.getImageUrl()).isEqualTo(expectedPet.getImageUrl());
    }

    //sad case create new dog
    @Test
    public void createPet_invalidRequest_throwsInvalidPetException() {
        // Given
        PetCreateRequest request = new PetCreateRequest();
        request.setName(null);
        request.setAge(2);
        request.setPetType(DOG);
        request.setImageUrl(null);

        // When and then
        assertThrows(InvalidPetException.class, () -> petService.createPet(request));
    }

    //sad case create new dog duplicate ID
    @Test
    public void testCreatePetWithDuplicateId() {
        String duplicatePetId = "existing-pet-id";
        PetCreateRequest request = new PetCreateRequest();
        when(petService.createPet(request)).thenThrow(new InvalidPetException("Pet with ID " + duplicatePetId + " already exists"));

        ResponseEntity<PetCreateResponse> response = petController.createPet(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    //happy case find by pet id

    @Test
    void findByPetId() {
        // GIVEN
//        String id = randomUUID().toString();
//
//        Pet record = new Pet();
//        record.setPetId(petId);
//        record.setName("name");

        // TODO - Fix call/methods for petService.getPetById
        // WHEN
//        when(petRepository.findById(id)).thenReturn(Optional.of(record));
//        Pet pet = petService.getId(id);
//
//        // THEN
//        Assertions.assertNotNull(pet, "The object is returned");
//        Assertions.assertEquals(record.getId(), pet.getId(), "The id matches");
//        Assertions.assertEquals(record.getName(), pet.getName(), "The name matches");
    }

    //sad case find by petId
    @Test
    void findByPetId_petNotFound_throwsInvalidPetException() {
        String petId = "nonexistentPetId";
        when(petRepository.findById(petId)).thenReturn(Optional.empty());

        assertThrows(InvalidPetException.class, () -> petService.findByPetId(petId));
    }

    //happy case update pet profile
    @Test
    void updatePet_succesfullyUpdates() {
        //given
        Pet existingPet = new Pet("PETID123", "Max", DOG, 5, "oldImageUrl");

        Pet updatedPet = new Pet("PETID123", "Sam", DOG, 6, "oldImageUrl");

        // setup findById to return existingPet
        when(petRepository.findById("PETID123")).thenReturn(Optional.of(existingPet));

        // setup save method to return updatedPet which simulates the pet update in repository
        when(petRepository.save(existingPet)).thenReturn(updatedPet);

        //when
        Pet resultPet = petService.updatePet(existingPet);

        // add a verification that save was called
        verify(petRepository, times(1)).save(any(Pet.class));

        //then
        Assertions.assertEquals("Sam", resultPet.getName());
        Assertions.assertEquals(6, resultPet.getAge());
        Assertions.assertEquals(DOG, resultPet.getPetType());
        Assertions.assertEquals("oldImageUrl", resultPet.getImageUrl());
    }

    //sad case update pet profile
    @Test
    void updatePet_shouldThrowInvalidPetException_whenPetNotFound() {
        String petId = "ABC123";
        Pet pet = new Pet();
        pet.setPetId(petId);

        when(petRepository.findById(petId)).thenReturn(Optional.empty());

        assertThrows(InvalidPetException.class, () -> petService.updatePet(pet));
    }

    //happy case find all pets

    //sad case find all pets

    //happy case delete pet
    @Test
    void testDeletePet_HappyCase() {
        String petId = "testPetId";

        petService.deletePet(petId);

        //  verify that the delete method was called on the repository
        verify(petRepository).deleteById(petId);
    }

    //sad case delete pet
    @Test
    public void testDeletePet_sadCase_repositoryThrowsException() {
        String petId = "somePetId";
        RuntimeException expectedException = new RuntimeException("Something went wrong!");
        doThrow(expectedException).when(petRepository).deleteById(petId);

        assertThrows(RuntimeException.class, () -> petService.deletePet(petId));

        // Verify  pet repository's delete method was called
        verify(petRepository).deleteById(petId);
    }

    //sad case delete pet
    @Test
    void deletePet_invalidPetId_throwsInvalidPetException() {
        // Given
        String petId = "nonexistentPetId";
        doThrow(new InvalidPetException("Invalid Input")).when(petRepository).deleteById(petId);

        // When, Then
        assertThrows(InvalidPetException.class, () -> petService.deletePet(petId));
    }

    //happy case converter petCreateResponse
    @Test
    public void convertToPetCreateResponse_mapsPetToResponse() {
        Pet pet = new Pet("D12345", "Milo", DOG, 1, "testURL");
        PetCreateResponse response = petService.convertToPetCreateResponse(pet);

        assertEquals(pet.getPetId(), response.getPetId());
        assertEquals(pet.getName(), response.getName());
        assertEquals(pet.getAge(), response.getAge());
    }



/**
 * ------------------------------------------------------------------------
 *
 * ------------------------------------------------------------------------
 **/


}
