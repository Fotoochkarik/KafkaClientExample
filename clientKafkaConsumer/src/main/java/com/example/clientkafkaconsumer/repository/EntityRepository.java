package com.example.clientkafkaconsumer.repository;

import com.example.clientkafkaconsumer.model.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface EntityRepository extends JpaRepository<Entity, Integer> {
}
