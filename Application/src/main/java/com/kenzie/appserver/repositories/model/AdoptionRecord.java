package com.kenzie.appserver.repositories.model;

import java.util.Objects;

public class AdoptionRecord {
    private String username;
    private String petId;
    private String dateOfAdoption;
    //private String amountDonated;

    public AdoptionRecord(String username, String petId, String dateOfAdoption) {
        this.username = username;
        this.petId = petId;
        this.dateOfAdoption = dateOfAdoption;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdoptionRecord that = (AdoptionRecord) o;
        return Objects.equals(username, that.username) && Objects.equals(petId, that.petId) && Objects.equals(dateOfAdoption, that.dateOfAdoption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, petId, dateOfAdoption);
    }
}
