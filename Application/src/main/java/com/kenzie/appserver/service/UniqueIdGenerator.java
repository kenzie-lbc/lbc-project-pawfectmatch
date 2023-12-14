package com.kenzie.appserver.service;

public class UniqueIdGenerator {

    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generatePetId(String type) {
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
        return builder.toString();
    }

    public static String generateUserId(String role) {
        StringBuilder builder = new StringBuilder();
        // Determine the prefix based on the user role
        switch (role.toLowerCase()) {
            case "shelter":
                builder.append("N");
                break;
            case "adopter":
                builder.append("H");
                break;
            case "foster":
                builder.append("F");
                break;
            default:
                builder.append("U"); // For unspecified roles
                break;
        }
        // Generate the random alphanumeric part
        for (int i = 0; i < 8; i++) {
            int character = (int)(Math.random() * ALPHANUMERIC_STRING.length());
            builder.append(ALPHANUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
