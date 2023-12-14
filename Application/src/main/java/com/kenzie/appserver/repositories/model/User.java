package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "User")
public class User {

    private String uniqueId;
    private String name;
    private String role;

    public User() {
    }


    @DynamoDBHashKey(attributeName = "Id")
    public String getId() {
        return uniqueId;
    }

    public String getRole() {
        return role;
    }

    @DynamoDBAttribute(attributeName = "Name")
    public String getName() {
        return name;
    }

    public void setId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User exampleRecord = (User) o;
        return Objects.equals(uniqueId, exampleRecord.uniqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId);
    }


}
