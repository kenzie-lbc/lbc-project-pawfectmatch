package com.kenzie.appserver.controller;

import com.kenzie.appserver.IntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.kenzie.appserver.controller.model.PetCreateRequest;
import com.kenzie.appserver.repositories.model.Pet;
import com.kenzie.appserver.service.PetService;

import net.andreinc.mockneat.MockNeat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;

//@IntegrationTest
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
}