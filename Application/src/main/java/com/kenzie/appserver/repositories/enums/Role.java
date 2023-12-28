package com.kenzie.appserver.repositories.enums;

public enum Role {
    SHELTER("Shelter"),
    ADOPTER("Adopter"),
    FOSTER("Foster");

    private final String role;

    Role(String roles) {
        this.role = roles;
    }

    public String getName() {
        return role;
    }

    // Static method to get enum from string
    // when parsing from DB or JSON object
    // ***REPLACE "name" with a better naming convention***
    public static Role fromString(String name) {
        for (Role thisRole : Role.values()) {
            if (thisRole.role.equalsIgnoreCase(name)) {
                return thisRole;
            }
        }
        throw new IllegalArgumentException("No such Role: " + name);
    }
}
