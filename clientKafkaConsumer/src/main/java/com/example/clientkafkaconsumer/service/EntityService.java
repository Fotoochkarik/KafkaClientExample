package com.example.clientkafkaconsumer.service;

import com.example.clientkafkaconsumer.model.Entity;
import com.example.clientkafkaconsumer.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityService {
    @Autowired
    private EntityRepository repository;

    public void createEntity(Entity entity) {
        repository.save(entity);
    }
}
