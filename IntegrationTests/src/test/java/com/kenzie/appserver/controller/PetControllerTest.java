package com.kenzie.appserver.controller;

import com.kenzie.appserver.IntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import com.kenzie.appserver.controller.model.PetCreateRequest;
import com.kenzie.appserver.repositories.enums.PetType;
import com.kenzie.appserver.repositories.model.Pet;
import com.kenzie.appserver.service.PetService;

import net.andreinc.mockneat.MockNeat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import java.time.LocalDate;
import java.util.UUID;

import static org.apache.commons.lang3.ArrayUtils.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class PetControllerTest {
    private static final MediaType JSON = MediaType.APPLICATION_JSON;

    @Autowired
    private MockMvc mvc;
    @Autowired
    private PetService petService;

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
    public void testDeletePetById_Success() throws Exception {
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
    public void testGetPetDetailsById_NonexistentPet() throws Exception {
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
}

