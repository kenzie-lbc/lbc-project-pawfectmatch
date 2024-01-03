package com.kenzie.appserver.service;

public class InvalidPetException extends RuntimeException {
    public InvalidPetException(String message) {
        super(message);
    }
}
