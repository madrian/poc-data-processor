package com.eidorian;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DataProcessor {
    final static String TABLE_NAME = "poc_data_feed";

    @Autowired
    private SimpleMessageListenerContainer listenerContainer;

    @Autowired
    private AmazonDynamoDB dynamoDB;

    public static void main(String[] args) {
        SpringApplication.run(DataProcessor.class, args);
    }

    @SqsListener("poc-data-feed-queue")
    public void dataFeedListener(String data) {
        System.out.println("message received: " + data);
        processItem(data);
    }

    private void processItem(String txnId) {
        Map<String, AttributeValue> itemKey = new HashMap<>();
        itemKey.put("uuid", new AttributeValue(txnId));

        Map<String, AttributeValue> item = getItem(itemKey);
        System.out.println("data payload: " + item);

        Map<String, AttributeValueUpdate> updatedItem = new HashMap<>();
        updatedItem.put("status", new AttributeValueUpdate(new AttributeValue("COMPLETED"), AttributeAction.PUT));

        updateItem(itemKey, updatedItem);
        System.out.println("updated payload: " + getItem(itemKey));
    }

    private Map<String, AttributeValue> getItem(Map<String, AttributeValue> itemKey) {
        GetItemRequest request = new GetItemRequest()
                .withKey(itemKey)
                .withTableName(TABLE_NAME);
        Map<String, AttributeValue> item = dynamoDB.getItem(request).getItem();
        return item;
    }

    private void updateItem(Map<String, AttributeValue> itemKey, Map<String, AttributeValueUpdate> updatedItem) {
        dynamoDB.updateItem(TABLE_NAME, itemKey, updatedItem);
    }

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSqs) {
        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setWaitTimeOut(5);
        return factory;
    }
}
