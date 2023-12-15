package com.kodilla.ecommercee.model.repository;

import com.kodilla.ecommercee.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
