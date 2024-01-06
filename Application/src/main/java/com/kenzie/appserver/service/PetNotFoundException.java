package com.kenzie.appserver.service;

public class PetNotFoundException extends RuntimeException{
        public PetNotFoundException(String id) {
            super("Could not find pet " + id);
        }
}
