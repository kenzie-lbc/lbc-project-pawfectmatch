package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.repositories.enums.PetType;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotEmpty;

public class PetCreateRequest {
    @JsonProperty("petId")
    private String petId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("petType")
    private PetType petType;

    @JsonProperty("age")
    private int age;
    @JsonProperty("imageUrl")
    private String imageUrl;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType type) {
        this.petType = petType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }
}
