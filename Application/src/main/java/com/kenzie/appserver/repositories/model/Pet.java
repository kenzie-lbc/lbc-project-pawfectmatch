package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "Pet")
public class Pet {

    private String id;
    private String name;
    private String type;

    public Pet() {
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
