package com.kenzie.appserver.service.exceptions;


public class InvalidPetException extends RuntimeException {
    public InvalidPetException(String message) {
        super(message);
    }
}
