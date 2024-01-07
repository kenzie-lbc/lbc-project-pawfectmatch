package com.kenzie.appserver.service;

public class PetAlreadyAdoptedException extends RuntimeException{
    public PetAlreadyAdoptedException(String id) {
        super("This pet has already found a home!  " + id);
    }
}
