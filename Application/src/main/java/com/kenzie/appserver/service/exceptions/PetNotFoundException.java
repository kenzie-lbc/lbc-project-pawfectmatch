package com.kenzie.appserver.service.exceptions;

public class PetNotFoundException extends RuntimeException{
        public PetNotFoundException(String petId) {
            super("Could not find pet " + petId);
        }
}
