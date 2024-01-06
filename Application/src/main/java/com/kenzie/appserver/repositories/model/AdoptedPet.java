package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.xspec.S;

@DynamoDBTable(tableName = "Adopted Pet")
public class AdoptedPet {
    private String id;
    private String ownerUsername;
    private String dateOfAdoption;
    private String petName;
    //private String pricePaid;

    AdoptedPet(String id, String ownerUsername, String dateOfAdoption, String petName) {
        this.id = id;
        this.ownerUsername = ownerUsername;
        this.dateOfAdoption = dateOfAdoption;
        this.petName = petName;
    }

    @DynamoDBHashKey(attributeName = "Id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "Username")
    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    @DynamoDBAttribute(attributeName = "Date of Adoption")
    public String getDateOfAdoption() {
        return dateOfAdoption;
    }

    public void setDateOfAdoption(String dateOfAdoption) {
        this.dateOfAdoption = dateOfAdoption;
    }

    @DynamoDBAttribute(attributeName = "Pet Name")
    public String getpetName() {
        return petName;
    }

    public void setpetName(String name) {
        this.petName = name;
    }
}
