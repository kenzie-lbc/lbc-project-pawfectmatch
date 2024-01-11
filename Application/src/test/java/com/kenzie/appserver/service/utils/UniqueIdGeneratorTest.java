package com.kenzie.appserver.service.utils;

import com.kenzie.appserver.repositories.enums.PetType;
import com.kenzie.appserver.repositories.enums.Role;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UniqueIdGeneratorTest {
    private DynamoDBHelper dynamoDBHelperMock;
    private UniqueIdGenerator uniqueIdGenerator;

    @BeforeEach
    void setUp() {
        dynamoDBHelperMock = Mockito.mock(DynamoDBHelper.class);
        uniqueIdGenerator = new UniqueIdGenerator(dynamoDBHelperMock);
    }

    @Test
    void generatePetId_withValidPetType_shouldGenerateUniqueId() {
        // given
        when(dynamoDBHelperMock.doesRecordExist(anyString(), anyString(), anyString())).thenReturn(false);

        // when
        String petId = uniqueIdGenerator.generatePetId(PetType.DOG);

        // then
        assertNotNull(petId);
        assertTrue(petId.startsWith("D"));
        assertEquals(9, petId.length());
    }

    @Test
    void generatePetId_withNullPetType_shouldThrowException() {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> uniqueIdGenerator.generatePetId(null));
    }

    @Test
    void generatePetId_withExistingId_shouldRegenerateUniqueId() {
        // given
        when(dynamoDBHelperMock.doesRecordExist(anyString(), anyString(), anyString()))
                .thenReturn(true).thenReturn(false);

        // when
        String petId = uniqueIdGenerator.generatePetId(PetType.CAT);

        //then
        assertNotNull(petId);
        assertTrue(petId.startsWith("C"));
        assertEquals(9, petId.length());
    }

    @Test
    void generateUserId_withValidRole_shouldGenerateUniqueId() {
        // given
        when(dynamoDBHelperMock.doesRecordExist(anyString(), anyString(), anyString())).thenReturn(false);

        // when
        String userId = uniqueIdGenerator.generateUserId(Role.ADOPTER);

        // then
        assertNotNull(userId);
        assertTrue(userId.startsWith("A"));
        assertEquals(9, userId.length());
    }

    @Test
    void generateUserId_withNullRole_shouldThrowException() {
        //when & then
        assertThrows(IllegalArgumentException.class, () -> uniqueIdGenerator.generateUserId(null));
    }

    @Test
    void generateUserId_withExistingId_shouldRegenerateUniqueId() {
        // given
        when(dynamoDBHelperMock.doesRecordExist(anyString(), anyString(), anyString()))
                .thenReturn(true).thenReturn(false);

        // when
        String userId = uniqueIdGenerator.generateUserId(Role.FOSTER);

        // then
        assertNotNull(userId);
        assertTrue(userId.startsWith("G"));
        assertEquals(9, userId.length());
    }

    @Test
    void generatePetId_forReptile_shouldGenerateUniqueId() {
        // given
        when(dynamoDBHelperMock.doesRecordExist(anyString(), anyString(), anyString())).thenReturn(false);

        // when
        String petId = uniqueIdGenerator.generatePetId(PetType.REPTILE);

        // then
        assertNotNull(petId);
        assertTrue(petId.startsWith("R"));
        assertEquals(9, petId.length());
    }

    @Test
    void generatePetId_forBird_shouldGenerateUniqueId() {
        // given
        when(dynamoDBHelperMock.doesRecordExist(anyString(), anyString(), anyString())).thenReturn(false);

        // when
        String petId = uniqueIdGenerator.generatePetId(PetType.BIRD);

        // then
        assertNotNull(petId);
        assertTrue(petId.startsWith("B"));
        assertEquals(9, petId.length());
    }

    @Test
    void generatePetId_forFish_shouldGenerateUniqueId() {
        // given
        when(dynamoDBHelperMock.doesRecordExist(anyString(), anyString(), anyString())).thenReturn(false);

        // when
        String petId = uniqueIdGenerator.generatePetId(PetType.FISH);

        // then
        assertNotNull(petId);
        assertTrue(petId.startsWith("F"));
        assertEquals(9, petId.length());
    }
}
