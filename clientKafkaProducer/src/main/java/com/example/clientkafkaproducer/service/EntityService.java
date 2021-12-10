package com.example.clientkafkaproducer.service;

import com.example.clientkafkaproducer.model.Entity;
import com.example.clientkafkaproducer.repository.EntityRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

@Service
public class EntityService {
    @Autowired
    private EntityRepository repository;

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    ObjectMapper mapper;

    public void createEntity(Entity entity) {
        repository.save(entity);
    }

    public List<Entity> getAll() {
        return repository.findAll();
    }

    public void sendMsg(Entity entity) {
        String msg;
        try {
            msg = mapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + entity + "'", e);
        }
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send("msg", entity.getId(), msg);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
