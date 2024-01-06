package com.kenzie.appserver.repositories.model;


import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.kenzie.appserver.repositories.enums.PetType;

import org.springframework.data.annotation.Id;

import java.util.Objects;
import javax.validation.constraints.NotBlank;

@DynamoDBTable(tableName = "Pet")
public class Pet {
    @Id
    @DynamoDBHashKey(attributeName = "petId")
    private String petId;
//    private String breed; //for queries?
    @DynamoDBAttribute(attributeName = "age")
    private int age;
    @DynamoDBTypeConvertedEnum
    private PetType petType;
    //stores uniqueId (shelter/foster)
//    private String adoptionId;
    @DynamoDBAttribute(attributeName = "name")
    private String name;
    @DynamoDBAttribute(attributeName = "imageUrl")
    private String imageUrl = "";

    public Pet() {
    }

    public Pet(String petId, String name, PetType petType, int age,
//               String breed,
               String imageUrl) {
        this.petId = petId;
        this.name = name;
        this.petType = petType;
        this.age = age;
//        this.breed = breed;
        this.imageUrl = imageUrl;
    }

    public String getPetId() {
        return petId;
    }

    public PetType getPetType() {
        return petType;
    }


    public String getName() {
        return name;
    }

    public void setPetId(String petId) {
        this.petId = petId;
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

//    public String getAdoptionId() {
//        return adoptionId;
//    }
//
//    public void setAdoptionId(String adoptionId) {
//        this.adoptionId = adoptionId;
//    }

//    public String getBreed() {
//        return breed;
//    }
//
//    public void setBreed(String breed) {
//        this.breed = breed;
//    }
//

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pet pet = (Pet) o;
        return Objects.equals(getPetId(), pet.getPetId())
                && Objects.equals(getName(), pet.getName())
                // ensure that the values compared can't be null or handle null values
                && getPetType() == pet.getPetType();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPetId(), getName(), getPetType());
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
