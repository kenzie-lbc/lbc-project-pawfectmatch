package com.kenzie.appserver.service.exceptions;

public class PetAlreadyAdoptedException extends RuntimeException{
    public PetAlreadyAdoptedException(String petId) {
        super("This pet has already found a home!  " + petId);
    }
}
