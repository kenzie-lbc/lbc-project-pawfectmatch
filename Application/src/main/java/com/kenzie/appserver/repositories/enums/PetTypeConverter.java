package com.kenzie.appserver.repositories.enums;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.kenzie.appserver.repositories.enums.PetType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import com.kenzie.ata.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class PetTypeConverter implements DynamoDBTypeConverter<String, PetType> {
    @Override
    public String convert(PetType petType) {
        return petType.toString();
    }

    @Override
    public PetType unconvert(String petType) {
        return PetType.valueOf(petType);
    }
}
