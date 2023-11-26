package com.kodilla.ecommercee.model.repository;

import com.kodilla.ecommercee.model.entity.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericEntityRepository extends JpaRepository<GenericEntity, Long> {

}
