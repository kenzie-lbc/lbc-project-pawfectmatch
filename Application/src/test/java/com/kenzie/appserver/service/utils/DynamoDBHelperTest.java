package com.kenzie.appserver.service.utils;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DynamoDBHelperTest {

    private AmazonDynamoDB amazonDynamoDBMock;
    private DynamoDBHelper dynamoDBHelper;

    @BeforeEach
    public void setUp() {
        amazonDynamoDBMock = Mockito.mock(AmazonDynamoDB.class);
        dynamoDBHelper = new DynamoDBHelper(amazonDynamoDBMock);
    }

    @Test
    public void doesRecordExist_whenRecordExists_shouldReturnTrue() {
        // given
        String tableName = "TestTable";
        String keyName = "TestId";
        String id = "123";
        Map<String, AttributeValue> item = Collections.singletonMap(keyName, new AttributeValue().withS(id));
        QueryResult queryResult = new QueryResult().withItems(Collections.singletonList(item));
        when(amazonDynamoDBMock.query(any(QueryRequest.class))).thenReturn(queryResult);

        // when
        boolean result = dynamoDBHelper.doesRecordExist(tableName, keyName, id);

        // then
        assertTrue(result);
    }

    @Test
    public void doesRecordExist_whenRecordDoesNotExist_shouldReturnFalse() {
        // given
        String tableName = "TestTable";
        String keyName = "TestId";
        String petId = "123";


        // when
        DynamoDBHelper dynamoDBHelperWithNullAmazonDynamoDB = new DynamoDBHelper(null);

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            dynamoDBHelperWithNullAmazonDynamoDB.doesRecordExist(tableName, keyName, petId);
        });    }

}
