package com.example.clientkafkaconsumer;

import com.example.clientkafkaconsumer.model.Entity;
import com.example.clientkafkaconsumer.service.EntityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@SpringBootApplication
public class ClientKafkaConsumerApplication {
    @Autowired
    private EntityService entityService;
    @Autowired
    ObjectMapper mapper;

    @KafkaListener(topics = "msg")
    public void orderListener(ConsumerRecord<Integer, String> record) {
        Entity entity;
        try {
            entity = mapper.readValue(record.value(), Entity.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid read from JSON:\n'" + "'", e);
        }
        entityService.createEntity(entity);
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientKafkaConsumerApplication.class, args);
    }

}
