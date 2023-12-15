package com.kenzie.appserver.service;

import java.util.HashSet;
import java.util.Set;

public class UniqueIdGenerator {

    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Set<String> existingIds = new HashSet<>(); // Temporary storage for existing IDs

    public static String generatePetId(String type) {
        String id;
        do {
            StringBuilder builder = new StringBuilder();
            // Determine the prefix based on the type of pet
            switch (type.toLowerCase()) {
                case "dog":
                    builder.append("D");
                    break;
                case "cat":
                    builder.append("C");
                    break;
                case "reptile":
                    builder.append("R");
                    break;
                case "bird":
                    builder.append("B");
                    break;
                case "fish":
                    builder.append("F");
                    break;
                default:
                    builder.append("X"); // Prefix for exotic or unspecified pets
                    break;
            }
            // Generate the random alphanumeric part
            for (int i = 0; i < 8; i++) {
                int character = (int)(Math.random() * ALPHANUMERIC_STRING.length());
                builder.append(ALPHANUMERIC_STRING.charAt(character));
            }
            id = builder.toString();
        } while (idExists(id)); // Ensure the ID is unique

        /**TODO Add to existing IDs, remove this line when integrating with a real database*/
        existingIds.add(id);
        return id;
    }

    public static String generateUserId(String role) {
        String id;
        do {
            StringBuilder builder = new StringBuilder();
            // Determine the prefix based on the user role
            switch (role.toLowerCase()) {
                case "shelter":
                    builder.append("S");
                    break;
                case "adopter":
                    builder.append("A");
                    break;
                case "foster":
                    builder.append("F");
                    break;
                default:
                    builder.append("U"); // For unspecified roles + future development + unexpected behavior
                    break;
            }
            // Generate the random alphanumeric part
            for (int i = 0; i < 8; i++) {
                int character = (int)(Math.random() * ALPHANUMERIC_STRING.length());
                builder.append(ALPHANUMERIC_STRING.charAt(character));
            }
            id = builder.toString();
        } while (idExists(id)); // Ensure the ID is unique

        /**TODO Add to existing IDs, remove this line when integrating with a real database*/
        existingIds.add(id); //
        return id;
    }
    public static boolean idExists(String id) {
        /** TODO Replace this with an actual database query that searches in th
         * database for a record with the 'id' and return true if found **/
        return existingIds.contains(id); // Temporary check against the existingIds set
    }
}
