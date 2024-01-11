package com.kenzie.appserver.repositories.model;


import com.kenzie.appserver.repositories.enums.Role;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;
import com.kenzie.ata.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
@DynamoDBTable(tableName = "User")
public class User {

    private String userId;
    private String username;
    private Role role;
    private String password;
    private Boolean adopted;

    public User() {
    }


    @DynamoDBHashKey(attributeName = "Id")
    public String getUserId() {
        return userId;
    }

    public Role getRole() {
        return role;
    }

    @DynamoDBAttribute(attributeName = "Username")
    public String getName() {
        return username;
    }

    public void setId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.username = name;
    }

    public void setRole(Role role) {
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
        return Objects.equals(userId, exampleRecord.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdopted() {
        return adopted;
    }

    public void setAdopted(Boolean adopted) {
        this.adopted = adopted;
    }
}
