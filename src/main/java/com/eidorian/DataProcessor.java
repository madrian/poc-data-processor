package com.eidorian;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataProcessor {

    @Autowired
    private SimpleMessageListenerContainer listenerContainer;

    public static void main(String[] args) {
        SpringApplication.run(DataProcessor.class, args);
    }

    @SqsListener("poc-data-feed-queue")
    public void dataFeedListener(String data) {
        System.out.println("message received: " + data);
    }

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSqs) {
        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setWaitTimeOut(5);
        return factory;
    }

//    @PostConstruct
//    public void setUpListenerContainer() {
//        listenerContainer.setQueueStopTimeout(25000);
//    }
}
