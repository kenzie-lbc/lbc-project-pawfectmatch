package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "User")
public class User {

    private String uniqueId;
    private String username;
    private String role;
    private String password;

    public User() {
    }


    @DynamoDBHashKey(attributeName = "Id")
    public String getId() {
        return uniqueId;
    }

    public String getRole() {
        return role;
    }

    @DynamoDBAttribute(attributeName = "Username")
    public String getName() {
        return username;
    }

    public void setId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setName(String name) {
        this.username = name;
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


    public Object getPassword() {
        return password;
    }
}
