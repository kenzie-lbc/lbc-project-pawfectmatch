package com.kenzie.appserver.service;

public class PetNotFoundException extends RuntimeException{
        public PetNotFoundException(String petId) {
            super("Could not find pet " + petId);
        }
}
