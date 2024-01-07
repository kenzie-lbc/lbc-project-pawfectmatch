package com.kenzie.appserver.service.utils;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DynamoDBHelper {
    private final AmazonDynamoDB amazonDynamoDB;

    public DynamoDBHelper(AmazonDynamoDB amazonDynamoDB){
        this.amazonDynamoDB = amazonDynamoDB;
    }

    public boolean doesRecordExist(String tableName, String keyName, String id) {
        Map<String, AttributeValue> queryParams = new HashMap<>();
        queryParams.put(":id", new AttributeValue().withS(id));
        QueryRequest queryRequest = new QueryRequest()
                .withTableName(tableName)
                .withKeyConditionExpression(keyName + " = :id")
                .withExpressionAttributeValues(queryParams);
        QueryResult result = amazonDynamoDB.query(queryRequest);

        return !result.getItems().isEmpty();
    }
}
