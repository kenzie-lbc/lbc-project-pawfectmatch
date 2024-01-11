package com.kenzie.appserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kenzie.appserver.IntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import com.kenzie.appserver.controller.model.PetCreateRequest;
import com.kenzie.appserver.controller.model.PetCreateResponse;
import com.kenzie.appserver.repositories.enums.PetType;
import com.kenzie.appserver.repositories.model.Pet;
import com.kenzie.appserver.service.PetService;

import com.kenzie.appserver.service.exceptions.InvalidPetException;
import com.kenzie.appserver.service.utils.UniqueIdGenerator;
import net.andreinc.mockneat.MockNeat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.apache.commons.lang3.ArrayUtils.newInstance;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.context.junit4.SpringRunner;
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
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.ArrayUtils.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
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
    private MockNeat mockNeat;
    private ObjectMapper mapper;



    @Mock
    private UniqueIdGenerator uniqueIdGenerator;

    @BeforeEach
    public void setUp() {
        mockNeat = MockNeat.threadLocal();
        mapper = createObjectMapper();
        uniqueIdGenerator = mock(UniqueIdGenerator.class);

        // Define a behavior: when generatePetId() is called, return a "static" petId"
        when(uniqueIdGenerator.generatePetId(any(PetType.class))).thenReturn("PETID123");

    }

    private ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Test
    public void getById_Exists() throws Exception {
        //Pet pet = new Pet();
        String id = "PETID123";
        String name = "Spike";
        int age = 1;
        PetType type = PetType.DOG;
        String imageUrl = UUID.randomUUID().toString();

        PetCreateRequest petCreateRequest = new PetCreateRequest();
        petCreateRequest.setName(name);
        petCreateRequest.setAge(age);
        petCreateRequest.setImageUrl(imageUrl);
        petCreateRequest.setPetType(type);
        petCreateRequest.setPetId("PETID123");

        Pet persistedPet = petService.createPet(petCreateRequest);
        mvc.perform(get("/Pet/petId/{petId}", persistedPet.getPetId())
                        .accept(JSON))
                .andExpect(jsonPath("name")
                        .value(is(name)))
                .andExpect(jsonPath("age")
                        .value(is(age)))
                .andExpect(status().isOk());
    }

    @Test
    public void createPet_CreateSuccessful() throws Exception {

        String id = "PETID123";
        String name = "Spike";
        int age = 1;
        PetType type = PetType.DOG;
        String imageUrl = UUID.randomUUID().toString();

        PetCreateRequest petCreateRequest = new PetCreateRequest();
        petCreateRequest.setName(name);
        petCreateRequest.setAge(age);
        petCreateRequest.setImageUrl(imageUrl);
        petCreateRequest.setPetType(type);
        petCreateRequest.setPetId("PETID123");

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(post("/Pet")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(petCreateRequest)))
                // THEN
                .andExpect(jsonPath("petId")
                        .exists())
                .andExpect(jsonPath("name")
                        .value(is(name)))
                .andExpect(jsonPath("age")
                        .exists())
                .andExpect(status().isCreated());

    }


    @Test
    public void testDeletePetById_Success() throws Exception{
        // GIVEN
        String id = "PETID123";
        String name = "Spike";
        int age = 1;
        PetType type = PetType.DOG;
        String imageUrl = UUID.randomUUID().toString();

        PetCreateRequest petCreateRequest = new PetCreateRequest();
        petCreateRequest.setPetId("PETID123");
        petCreateRequest.setPetType(type);
        petCreateRequest.setAge(age);
        petCreateRequest.setName(name);
        petCreateRequest.setImageUrl(imageUrl);

        Pet pet = petService.createPet(petCreateRequest);


        //WHEN
        mvc.perform(delete("/Pet/petId/{petId}/", pet.getPetId())
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNoContent());

        //assertThat(petService.findByPetId(id)).isNull();

    }

    @Test
    public void testDeletePetByID_NonexistentPet() throws Exception {
        // GIVEN

        String id = "invalidId";

        PetCreateRequest petCreateRequest = new PetCreateRequest();
        petCreateRequest.setPetId(id);

        mvc.perform(delete("/petId/{petId}", id)
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNotFound());


    }

    @Test
    public void testGetPetDetailsById_Success() throws Exception {

        String name = "Fido";
        int age = 1;
        String imageUrl = UUID.randomUUID().toString();
        PetType type = PetType.DOG  ;

        PetCreateRequest petCreateRequest = new PetCreateRequest();
        petCreateRequest.setPetId("PETID123");
        petCreateRequest.setPetType(type);
        petCreateRequest.setAge(age);
        petCreateRequest.setName(name);
        petCreateRequest.setImageUrl(imageUrl);

        Pet pet = petService.createPet(petCreateRequest);

        mapper.registerModule(new JavaTimeModule());

        assertEquals(pet.getName(), name);
        assertEquals(pet.getAge(), age);

        mvc.perform(
                        get("/Pet/petId/{petId}", pet.getPetId())
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(is(name)))
                .andExpect(jsonPath("age").value(is(age)));
//


    }

    @Test
    public void testSearchPetsById_InvalidId() throws Exception {
        // GIVEN
        String invalidId = "invalidId";

        mvc.perform(get("/petId/{petId}", invalidId)
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNotFound());


    }

    @Test
    public void testGetPetDetailsById_NonexistentPet() throws Exception {
        // GIVEN
        String id = UUID.randomUUID().toString();
        //WHEN
        mvc.perform(get("/petId/{petId}", id)
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNotFound());


    }

    @Test
    public void testUpdatePetProfile_Success() throws Exception {
        //GIVEN
//    String petId = "PETID123";
//    String name = "Fluffy";
//    PetType type = PetType.DOG;
//    int age = 3;
//    String imageUrl = UUID.randomUUID().toString();
//
//    Pet existingPet = new Pet(petId, name, type, age, imageUrl);
//
//    String newName = "Fluffles";
//
//    PetCreateRequest petCreateRequest = new PetCreateRequest();
//    petCreateRequest.setPetId(petId);
//    petCreateRequest.setPetType(type.toString());
//    petCreateRequest.setAge(age);
//    petCreateRequest.setName(newName);
//    petCreateRequest.setImageUrl(imageUrl);
//
//      //  UpdatePetRequest???
//
//    MultipartFile file = mock(MultipartFile.class);
//    Pet updatedPet = new Pet(petId, newName, type, age, imageUrl);
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
        petCreateRequest1.setPetId("PETID123");
        petCreateRequest1.setName("Binx");
        petCreateRequest1.setPetType(PetType.CAT);
        petCreateRequest1.setImageUrl(UUID.randomUUID().toString());
        petCreateRequest1.setAge(3);

        Pet persistedPet1 = petService.createPet(petCreateRequest1);

        PetCreateRequest petCreateRequest2 = new PetCreateRequest();
        petCreateRequest2.setPetId("PETID124");
        petCreateRequest2.setPetType(PetType.CAT);
        petCreateRequest2.setName("Louie");
        petCreateRequest2.setImageUrl(UUID.randomUUID().toString());
        petCreateRequest2.setAge(5);

        Pet persistedPet2 = petService.createPet(petCreateRequest2);

        // WHEN
        ResultActions actions = mvc.perform(get("/Pet/petType/{petType}", PetType.CAT)
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
//        PetType type = PetType.valueOf("null");
//
//        mvc.perform(get("/petType/{petType}", null)
//                        .accept(MediaType.APPLICATION_JSON))
//                // THEN
//                .andExpect(status().isNotFound());
    }





    /**
     * ------------------------------------------------------------------------
     * PET CONTROLLER TESTS
     * ------------------------------------------------------------------------
     **/


    //User story:   As a shelter, I want to add new dog profiles to the platform.
    //happy case
//    @Test
//    public void getAllPets_Sucessful() throws Exception {
//        PetCreateRequest petCreateRequest1 = new PetCreateRequest();
//        petCreateRequest1.setPetId("PETID123");
//        petCreateRequest1.setName("Binx");
//        petCreateRequest1.setPetType(PetType.CAT);
//        petCreateRequest1.setImageUrl(UUID.randomUUID().toString());
//        petCreateRequest1.setAge(3);
//
//        Pet persistedPet1 = petService.createPet(petCreateRequest1);
//
//        PetCreateRequest petCreateRequest2 = new PetCreateRequest();
//        petCreateRequest2.setPetId("PETID123");
//        petCreateRequest2.setPetType(PetType.DOG);
//        petCreateRequest2.setName("Louie");
//        petCreateRequest2.setImageUrl(UUID.randomUUID().toString());
//        petCreateRequest2.setAge(5);
//
//        Pet persistedPet2 = petService.createPet(petCreateRequest2);
//
//        ResultActions actions = mvc.perform(get("/Pets")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        String responseBody = actions.andReturn().getResponse().getContentAsString();
//        List<PetCreateResponse> listAllPets = mapper.readValue(responseBody, new TypeReference<List<PetCreateResponse>>() {
//        });
//        assertThat(listAllPets.size()).isEqualTo(2);
//
//    }





}


