package com.kenzie.appserver.controller.model;

public class AdoptionResponse {
    private String username;
    private String petId;
    private String dateOfAdoption;
    //private String amountDonated;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getDateOfAdoption() {
        return dateOfAdoption;
    }

    public void setDateOfAdoption(String dateOfAdoption) {
        this.dateOfAdoption = dateOfAdoption;
    }
}
