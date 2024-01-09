package com.kenzie.appserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kenzie.appserver.IntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import com.kenzie.appserver.controller.model.PetCreateRequest;
import com.kenzie.appserver.controller.model.PetCreateResponse;
import com.kenzie.appserver.repositories.PetRepository;
import com.kenzie.appserver.repositories.enums.PetType;
import com.kenzie.appserver.repositories.model.Pet;
import com.kenzie.appserver.service.PetService;

import net.andreinc.mockneat.MockNeat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.apache.commons.lang3.ArrayUtils.newInstance;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;

//@IntegrationTest


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.repositories.model.Pet;
import com.kenzie.appserver.service.PetService;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.apache.commons.lang3.ArrayUtils.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@IntegrationTest
class PetControllerTest {
    private static final MediaType JSON = MediaType.APPLICATION_JSON;

    @Autowired
    private MockMvc mvc;
    @Autowired
    private PetService petService;
    @MockBean
    private PetRepository petRepository;
    @MockBean
    private PetController petController;
    @MockBean
    private PetCreateRequest petCreateRequest;
    private MockNeat mockNeat;
    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        mockNeat = MockNeat.threadLocal();
        mapper = createObjectMapper();
    }

    private ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Test
    public void getById_Exists() throws Exception {
//        Pet pet = new Pet();
//        Pet persistedPet = petService.createPet(pet);
//        mvc.perform(get("/Pet/{petId}", persistedPet.getPetId())
//                        .accept(JSON))
//                .andExpect(jsonPath("$.id")
//                        .value(is(persistedPet.getPetId())))
//                .andExpect(jsonPath("$.name")
//                        .value(is(pet.getName())))
//                .andExpect(status().isOk());
    }

    @Test
    public void createPet_CreateSuccessful() throws Exception {
//        PetCreateRequest petCreateRequest = new PetCreateRequest();
//        petCreateRequest.setName(mockNeat.strings().valStr());
//
//        mvc.perform(post("/Pet")
//                        .accept(JSON)
//                        .contentType(JSON)
//                        .content(mapper.writeValueAsString(petCreateRequest)))
//                .andExpect(jsonPath("$.id")
//                        .exists())
//                .andExpect(jsonPath("$.name")
//                        .value(is(petCreateRequest.getName())))
//                .andExpect(status().isCreated());
    }

    @Test
    public void testDeletePetById_Success() {
        // GIVEN
//       String id = UUID.randomUUID().toString();
//       String adoptionId = UUID.randomUUID().toString();
//       String name = mockNeat.strings().valStr();
//       int age = 1;
//       PetType type = PetType.DOG;
//
//       Pet pet = new Pet(id, adoptionId, type, age, name);
//
//       // WHEN
//       mvc.perform(delete("/petId/{petId}", pet.getPetId())
//                       .accept(MediaType.APPLICATION_JSON))
//               // THEN
//               .andExpect(status().isNoContent());
//       assertThat(petService.findByPetId(id)).isNull();

    }

    @Test
    public void testDeletePetByID_NonexistentPet() throws Exception {
        // GIVEN

//       String id = UUID.randomUUID().toString();
//
//       PetCreateRequest petCreateRequest = new PetCreateRequest();
//       petCreateRequest.setPetId(id);
//
//       mapper.registerModule(new JavaTimeModule());
//
//       // WHEN
//       mvc.perform(delete("/petId/{petId}")
//                       .accept(MediaType.APPLICATION_JSON)
//                       .contentType(MediaType.APPLICATION_JSON)
//                      .content(mapper.writeValueAsString(petCreateRequest)))
//               // THEN
//               .andExpect(status().isBadRequest());


    }

    @Test
    public void testGetPetDetailsById_Success() throws Exception {

//       String id = UUID.randomUUID().toString();
//       String name = "Fido";
//       int age = 1;
//       String imageUrl = UUID.randomUUID().toString();
//       PetType type = PetType.DOG;
//
//       Pet pet = new Pet(id, name, type, age, imageUrl);
//       Pet persistedPet = petService.findByPetId(id);
//
//
//       assertEquals(persistedPet.getName(), name);
//       assertEquals(persistedPet.getAge(), age);
//       assertEquals(persistedPet.getPetType(), type);


//       mvc.perform(
//                       get("/Pet/{id}", id)
//                               .accept(MediaType.APPLICATION_JSON))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("adoptionId").value(is(adoptionId)))
//               .andExpect(jsonPath("name").value(is(name)))
//               .andExpect(jsonPath("age").value(is(age)))
//               .andExpect(jsonPath("type").value(is(type)));
//


    }

    @Test
    public void testSearchPetsById_InvalidId() throws Exception {
        // GIVEN
//       String invalidId = UUID.randomUUID().toString();
//
//       mapper.registerModule(new JavaTimeModule());

        // WHEN
//       mvc.perform(get("/Pet/{petId}")
//                       .accept(MediaType.APPLICATION_JSON)
//                       .contentType(MediaType.APPLICATION_JSON)
//                      .content(mapper.writeValueAsString(?CreateRequest)))
//               // THEN
//               .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetPetDetailsById_NonexistentPet() {
//        // GIVEN
//        String id = UUID.randomUUID().toString();
//
//        Pet persistedPet = petService.findByPetId(id);
//
//        ResultActions actions = mvc.perform(get("/petId/{petId}", persistedPet.getPetId())
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON))
//                // THEN
//                .andExpect(status().isNoContent());
//
//        assertNull(persistedPet.getPetId());
//        assertNull(persistedPet.getPetType());
//        //assertNull(persistedPet.getAge());
//        assertNull(persistedPet.getName());
//        assertNull(persistedPet.getImageUrl());


    }

    @Test
    public void testUpdatePetProfile_Success() throws Exception {
//// GIVEN
//    String petId = UUID.randomUUID().toString();
//    String name = "Fluffy";
//    PetType type = PetType.DOG;
//    int age = 3;
//    String imageUrl = UUID.randomUUID().toString();
//
//    Pet existingPet = new Pet(petId, name, type, age, imageUrl);
//
//    String newName = "Fluffles";

//    PetCreateRequest petCreateRequest = new PetCreateRequest();
//    petCreateRequest.setPetId(petId);
//    petCreateRequest.setPetType(type);
//    petCreateRequest.setAge(age);
//    petCreateRequest.setName(newName);
//    petCreateRequest.setImageUrl(imageUrl);

        //UpdatePetRequest???

//    MultipartFile file = mock(MultipartFile.class);
//    Pet updatedPet = new Pet(petId, newName, type, age, imageUrl))
//
//    //Pet updatedPet = petService.createPet(petCreateRequest);
//
//    Pet resultPet = petService.updatePet(updatedPet, file);
//
//    mapper.registerModule(new JavaTimeModule());
//
//    // WHEN
//    mvc.perform(put("/pet")
//                    .accept(MediaType.APPLICATION_JSON)
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(mapper.writeValueAsString(PetUpdateRequest)))
//            // THEN
//            .andExpect(jsonPath("petId")
//                    .exists())
//            .andExpect(jsonPath("name")
//                    .value(is(newName)))
//            .andExpect(jsonPath("petType")
//                    .value(is(type)))
//            .andExpect(jsonPath("age")
//                    .value(is(age)))
//            .andExpect(jsonPath("imageUrl")
//                    .value(is(imageUrl)))
//            .andExpect(status().isOk());
    }

    @Test
    public void testFilterPetsByBreed_SpecificBreed_Sucessful() throws Exception {
        PetCreateRequest petCreateRequest1 = new PetCreateRequest();
        petCreateRequest1.setPetId(UUID.randomUUID().toString());
        petCreateRequest1.setName("Binx");
        petCreateRequest1.setPetType(PetType.CAT);
        petCreateRequest1.setImageUrl(UUID.randomUUID().toString());
        petCreateRequest1.setAge(3);

        Pet persistedPet1 = petService.createPet(petCreateRequest1);

        PetCreateRequest petCreateRequest2 = new PetCreateRequest();
        petCreateRequest2.setPetId(UUID.randomUUID().toString());
        petCreateRequest2.setPetType(PetType.CAT);
        petCreateRequest2.setName("Louie");
        petCreateRequest2.setImageUrl(UUID.randomUUID().toString());
        petCreateRequest2.setAge(5);

        Pet persistedPet2 = petService.createPet(petCreateRequest2);

        // WHEN
        ResultActions actions = mvc.perform(get("/petType/{petType}", PetType.CAT)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // THEN
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        List<PetCreateResponse> listPetTypeCat = mapper.readValue(responseBody, new TypeReference<List<PetCreateResponse>>() {
        });
        assertThat(listPetTypeCat.size()).isEqualTo(2);


    }

    @Test
    public void testFilterPetsByBreed_SpecificBreed_DoesNotExist() throws Exception {
//        PetType type = PetType.valueOf(Null);
//
//        PetCreateRequest petCreateRequest = new PetCreateRequest();
//        petCreateRequest.setPetType(type);
//
//
//        mapper.registerModule(new JavaTimeModule());
//
//        // WHEN
//        mvc.perform(post("/reservedtickets")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(petCreateRequest)))
//                // THEN
//                .andExpect(status().isBadRequest());
    }

}


    /**
     * ------------------------------------------------------------------------
     * PET CONTROLLER TESTS
     * ------------------------------------------------------------------------
     **/


    //User story:   As a shelter, I want to add new dog profiles to the platform.
    //happy case
    @Test
    public void testCreateDogProfile_Success() throws Exception {
        // Preconditions: Authenticate user as shelter (implementation not shown here)

        // Mock the PetService to return a saved dog profile
        Pet savedDog = new Pet();
        savedDog.setPetId("new-dog-id");
        when(petService.createPet(any())).thenReturn(savedDog);

        // Create the request body
        String requestBody = "{\"name\": \"Popcorn\", \"age\": 3, \"breed\": \"Corgi\"}";

        // Perform the POST request
        mvc.perform(post("/api/dogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

        // Verification steps
        verify(petService).createPet(any());
        verify(petRepository).save(any()); // Assert that the dog profile is added to the database

        // Clean-up: Remove the created dog profile from the database (optional)
    }

    //User Story: As a shelter, I want to add new dog profiles to the platform.
    // sad case --  missing details
    @Test
    public void testCreateDogProfile_MissingDetails() throws Exception {
        // Preconditions: No need to explicitly set up a new pet, as we're testing a missing data scenario.
        // No authentication, future dev

        // Create a PetCreateRequest with missing details
        PetCreateRequest petCreateRequest = new PetCreateRequest();
        petCreateRequest.setName("Wishbone");  // Only provide the name detail, and omit all other details

        // Simulate the POST request with missing details
        mvc.perform(post("/api/dogs")
                        .contentType(JSON)
                        .content(mapper.writeValueAsString(petCreateRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Required fields are missing: [petId, age, petType]"));

        // Verification: Ensure no pet was added to the database
        verify(petRepository, never()).save(any(Pet.class));  // never() to verify no interaction
    }



//    //User Story: As a shelter, I want my clients to be able to search for pets by id
//    //happy case exact match
//    @Test
//    public void testSearchPetsById_ExactMatch() throws Exception {
//    //set test profile 1
//        Pet pet1 = petController.createPet();
//        pet1.setName("Purdy");
//        pet1.setPetId("D56789");
//        pet1.setAge(4);
//        petRepository.save(pet1);
//
//    //test profile 2
//        Pet pet2 = petController.createPet();
//        pet2.setName("Pongo");
//        pet2.setPetId("D12345");
//        pet2.setAge(5);
//        petRepository.save(pet2);
//
//        //when
//        mvc.perform(get("/api/pet/search?Id=D12345"))
//                .andDo(print()) //for debugging
//
//        //then
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1))) //returns only one pet
//                .andExpect(jsonPath("$[0].name", is("Pongo")))
//                .andExpect(jsonPath("$[0].petId", is("D12345")))
//                .andExpect((jsonPath("$[0].age", is(5))));
//    }
//


}
