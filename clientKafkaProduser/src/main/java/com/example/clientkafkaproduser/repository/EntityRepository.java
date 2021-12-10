package com.example.clientkafkaproduser.repository;

import com.example.clientkafkaproduser.model.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface EntityRepository extends JpaRepository<Entity, Integer> {
}
