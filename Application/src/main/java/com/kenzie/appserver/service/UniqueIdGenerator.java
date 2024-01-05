package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.enums.PetType;
import com.kenzie.appserver.repositories.enums.Role;

import java.util.HashSet;
import java.util.Set;

public class UniqueIdGenerator {

    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    // Temporary storage for existing IDs
    private static final Set<String> existingIds = new HashSet<>();

    public static String generatePetId(PetType petType) {
        String id;
        do {
            StringBuilder builder = new StringBuilder();
            // Determine the prefix based on the type of pet
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
            }
            // Generate the random alphanumeric part
            for (int i = 0; i < 8; i++) {
                int character = (int) (Math.random() * ALPHANUMERIC_STRING.length());
                builder.append(ALPHANUMERIC_STRING.charAt(character));
            }
            id = builder.toString();
            // Ensure the ID is unique
        } while (idExists(id));

        /**TODO Add to existing IDs, remove this line when integrating with a real database*/
        existingIds.add(id);
        return id;
    }

    public static String generateUserId(Role role) {
        String id;
        do {
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
            }
            // Generate the random alphanumeric part
            for (int i = 0; i < 8; i++) {
                int character = (int) (Math.random() * ALPHANUMERIC_STRING.length());
                builder.append(ALPHANUMERIC_STRING.charAt(character));
            }
            id = builder.toString();
            // Ensure the ID is unique
        } while (idExists(id));

        /**TODO Add to existing IDs, remove this line when integrating with a real database*/
        existingIds.add(id);
        return id;
    }

    public static boolean idExists(String id) {
        /** TODO Replace this with an actual database query that searches in the
         * database for a record with the 'id' and return true if found **/
        // Temporary check against the existingIds set
        return existingIds.contains(id);
    }
}
