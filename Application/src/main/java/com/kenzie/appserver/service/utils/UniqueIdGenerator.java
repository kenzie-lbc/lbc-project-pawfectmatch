package com.kenzie.appserver.service.utils;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;

import com.kenzie.appserver.repositories.enums.PetType;
import com.kenzie.appserver.repositories.enums.Role;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class UniqueIdGenerator {
    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final DynamoDBHelper dynamoDBHelper;


    // Temporary storage for existing IDs
    private final Set<String> existingIds = new HashSet<>();

    public UniqueIdGenerator(DynamoDBHelper dynamoDBHelper) {
        this.dynamoDBHelper = dynamoDBHelper;
    }
    public String generatePetId(PetType petType) {
        if (petType == null){
            throw new IllegalArgumentException("The petType parameter is null");
        }

        String uniqueId = "";

        do {
            uniqueId = createIdBasedOnPetType(petType);

        } while (dynamoDBHelper.doesRecordExist("Pet", "petId", uniqueId));;

        return uniqueId;
    }

    private String createIdBasedOnPetType(PetType petType) {
        StringBuilder builder = new StringBuilder();
        switch (petType) {
            case DOG:
                builder.append("D");
                break;
            case CAT:
                builder.append("C");
                break;
            case REPTILE:
                builder.append("R");
                break;
            case BIRD:
                builder.append("B");
                break;
            case FISH:
                builder.append("F");
                break;
            default:

                // Prefix for exotic or unspecified pets
                builder.append("X");
                break;
        } for (int i = 0; i < 8; i++) {
            int character = (int) (Math.random() * ALPHANUMERIC_STRING.length());
            builder.append(ALPHANUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    protected String generateUserId(Role role) {
        String uniqueId = "";

        QueryResult result;
        do {
            uniqueId = createIdBasedOnRole(role);

        } while (dynamoDBHelper.doesRecordExist("User", "uniqueId", uniqueId));;

        return uniqueId;
    }

    private String createIdBasedOnRole(Role role) {
        if (role == null){
            throw new IllegalArgumentException("The role parameter is null");

        }
        StringBuilder builder = new StringBuilder();
        // Determine the prefix based on the user role
        switch (role) {
            case SHELTER:
                builder.append("S");
                break;
            case ADOPTER:
                builder.append("A");
                break;
            case FOSTER:
                builder.append("G");
                break;
            default:

                // For unspecified roles + future development + unexpected behavior
                builder.append("U");
                break;
        } for (int i = 0; i < 8; i++) {
            int character = (int) (Math.random() * ALPHANUMERIC_STRING.length());
            builder.append(ALPHANUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
