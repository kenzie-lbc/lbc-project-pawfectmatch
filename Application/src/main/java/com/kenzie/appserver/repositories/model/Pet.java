package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "Pet")
public class Pet {

    private String id;
    private String adoptionId;
    private String name;
    private String type;
    private int age;

    private boolean isAdopted = Boolean.FALSE;

    public Pet(String id, String adoptionId, String name, String type, int age) {
        this.id = id;
        this.adoptionId = adoptionId;
        this.name = name;
        this.age = age;
        this.type = type;
    }


    @DynamoDBHashKey(attributeName = "Id")
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
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

    public void setType(String type) {
        this.type = type;
    }

    @DynamoDBAttribute(attributeName = "Age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @DynamoDBAttribute(attributeName = "AdoptionId")
    public String getAdoptionId() {
        return adoptionId;
    }

    public void setAdoptionId(String adoptionId) {
        this.adoptionId = adoptionId;
    }

    @DynamoDBAttribute(attributeName = "isAdopted")
    public boolean isAdopted() {
        return isAdopted;
    }

    public void setAdopted(boolean isAdopted) {
        isAdopted = isAdopted;
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


}
