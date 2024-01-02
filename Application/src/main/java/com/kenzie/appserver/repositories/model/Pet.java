package com.kenzie.appserver.repositories.model;

import com.kenzie.appserver.repositories.enums.PetType;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;
import javax.validation.constraints.NotBlank;

@DynamoDBTable(tableName = "Pet")
public class Pet {

    private String id;
    //stores uniqueId (shelter/foster)
    private String adoptionId;
    @NotBlank(message = "Pet name is required")
    private String name;
    private PetType petType;
    private int age;
    private String imageUrl;

    public Pet() {
    }

    public Pet(String id, String name, PetType petType, int age, String imageUrl) {
        this.id = id;
        this.name = name;
        this.petType = petType;
        this.age = age;
        this.imageUrl = imageUrl;
    }


    @DynamoDBHashKey(attributeName = "Id")
    public String getId() {
        return id;
    }

    public PetType getPetType() {
        return petType;
    }

    @DynamoDBAttribute(attributeName = "Name")
    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdoptionId() {
        return adoptionId;
    }

    public void setAdoptionId(String adoptionId) {
        this.adoptionId = adoptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pet exampleRecord = (Pet) o;
        return Objects.equals(id, exampleRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
