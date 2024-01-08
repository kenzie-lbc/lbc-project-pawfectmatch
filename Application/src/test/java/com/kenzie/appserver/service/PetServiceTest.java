package com.kenzie.appserver.service;


import com.cloudinary.Cloudinary;
import com.kenzie.appserver.controller.UserController;
import com.kenzie.appserver.controller.model.PetCreateRequest;
import com.kenzie.appserver.controller.model.PetCreateResponse;
import com.kenzie.appserver.repositories.PetRepository;
import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.enums.PetType;
import com.kenzie.appserver.repositories.model.Pet;

import com.kenzie.appserver.service.exceptions.InvalidPetException;
import com.kenzie.appserver.service.utils.UniqueIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Optional;

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


    @BeforeEach
    void setup() {
        petRepository = mock(PetRepository.class);

        petService = new PetService(petRepository, cloudinary, uniqueIdGenerator);
    }

    /**
     * ------------------------------------------------------------------------
     * exampleService.findById
     * ------------------------------------------------------------------------
     **/

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

//    @Test
//    void findByPetId_invalid() {
    // GIVEN
//        String id = randomUUID().toString();
//
//        when(petRepository.findById(id)).thenReturn(Optional.empty());

    // TODO - Fix call/methods for petService.getPetById
    // WHEN
//        Pet pet = petService.findById(id);
//
//        // THEN
//        Assertions.assertNull(pet, "The example is null when not found");
//    }

    //petservice unit testing

    //happy case create new dog
    @Test
    void createNewDogTest_validData() {
        //given
        //String petId = "D54321";
        String name = "Buddy";
        String type = "dog";
        int age = 3;
        String breed = "Labrador";

        PetCreateRequest petCreateRequest = new PetCreateRequest();

        Pet expectedPet = new Pet();
        //expectedPet.setPetId(petId);
        expectedPet.setName(name);
        expectedPet.setPetType(PetType.DOG);
        expectedPet.setAge(age);

        when(petRepository.save(any(Pet.class))).thenReturn(expectedPet);

        //when
        Pet createdPet = petService.createPet(petCreateRequest);

        //then
        assertThat(createdPet).isNotNull();
        assertThat(createdPet.getName()).isEqualTo(name);
        assertThat(createdPet.getAge()).isEqualTo(age);
        assertThat(createdPet.getPetType()).isEqualTo(PetType.DOG);
    }

    //sad case create new dog
    @Test
    public void createPet_invalidRequest_throwsInvalidPetException() {
        PetCreateRequest request = new PetCreateRequest();
        assertThrows(InvalidPetException.class, () -> petService.createPet(request));
    }

    //happy case find by pet id


    //sad case find by petId
    @Test
    void findByPetId_petNotFound_throwsInvalidPetException() {
        String petId = "nonexistentPetId";
        when(petRepository.findById(petId)).thenReturn(Optional.empty());

        assertThrows(InvalidPetException.class, () -> petService.findByPetId(petId));
    }

    //happy case update pet profile
    @Test
    public void updatePet_succesfullyUpdates() throws Exception {
        //given
        Pet existingPet = new Pet("123", "Max", PetType.DOG, 5, "oldImageUrl");
        petRepository.save(existingPet);

        Pet updatedPet = new Pet("123", "Max", PetType.DOG, 6, "newImageUrl");
        MultipartFile file = mock(MultipartFile.class); // Mock the file upload
        when(file.getBytes()).thenReturn("image data".getBytes());
        when(cloudinary.uploader().upload(any(), anyMap())).thenReturn(Collections.singletonMap("url", "uploadedImageUrl"));

        //then
        Pet resultPet = petService.updatePet(updatedPet, file);

        //when
        verify(cloudinary.uploader()).upload(any(), anyMap()); // Verify image upload
        assertEquals("Max", resultPet.getName());
        assertEquals(6, resultPet.getAge());
        assertEquals(PetType.DOG, resultPet.getPetType());
        assertEquals("uploadedImageUrl", resultPet.getImageUrl());
    }

    //sad case update pet profile
    @Test
    void updatePet_shouldThrowInvalidPetException_whenPetNotFound() {
        String petId = "ABC123";
        Pet pet = new Pet();
        pet.setPetId(petId);
        MultipartFile file = mock(MultipartFile.class);

        when(petRepository.findById(petId)).thenReturn(Optional.empty());

        assertThrows(InvalidPetException.class, () -> petService.updatePet(pet, file));
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

    //happy case converter petCreateResponse
    @Test
    public void convertToPetCreateResponse_mapsPetToResponse() {
        Pet pet = new Pet("D12345", "Milo", PetType.DOG, 1, "testURL");
        PetCreateResponse response = petService.convertToPetCreateResponse(pet);

        assertEquals(pet.getPetId(), response.getPetId());
        assertEquals(pet.getName(), response.getName());
        assertEquals(pet.getAge(), response.getAge());
    }



}
