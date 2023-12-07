package com.kodilla.ecommercee.model.repository;

import com.kodilla.ecommercee.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Cart, Long> {
}
