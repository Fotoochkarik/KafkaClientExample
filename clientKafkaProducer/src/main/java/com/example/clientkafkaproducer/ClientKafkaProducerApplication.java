package com.example.clientkafkaproducer;

import com.example.clientkafkaproducer.model.Entity;
import com.example.clientkafkaproducer.service.EntityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class ClientKafkaProducerApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ClientKafkaProducerApplication.class, args);
        EntityService service = ctx.getBean(EntityService.class);

        for (int i = 1; i < 1000; i++) {
            service.createEntity(new Entity("Entity " + i));
        }

        List<Entity> entitiesList = service.getAll();

        for (Entity entity : entitiesList) {
            service.sendMsg(entity);
        }
    }
}
