package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.PetRepository;
import com.kenzie.appserver.repositories.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PetServiceTest {
    private PetRepository petRepository;
    private PetService petService;

    @BeforeEach
    void setup() {
        petRepository = mock(PetRepository.class);
        petService = new PetService(petRepository);
    }
    /** ------------------------------------------------------------------------
     *  exampleService.findById
     *  ------------------------------------------------------------------------ **/

    @Test
    void findById() {
        // GIVEN
        String id = randomUUID().toString();

        Pet record = new Pet();
        record.setId(id);
        record.setName("concertname");

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

    @Test
    void findByConcertId_invalid() {
        // GIVEN
        String id = randomUUID().toString();

        when(petRepository.findById(id)).thenReturn(Optional.empty());

        // TODO - Fix call/methods for petService.getPetById
        // WHEN
//        Pet pet = petService.findById(id);
//
//        // THEN
//        Assertions.assertNull(pet, "The example is null when not found");
    }

}
