package com.kodilla.ecommercee.crudTests;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.model.repository.CartRepository;
import com.kodilla.ecommercee.model.repository.ProductRepository;
import com.kodilla.ecommercee.model.repository.UserRepository;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class CartTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void testCartSavedWithUser() {
        // Given
        User user = new User(null, "John", null);
        user.setUserName("John");
        user = userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);

        // When
        cart = cartRepository.save(cart);

        // Then
        assertNotNull(cart.getId());
        Cart savedCart = cartRepository.findById(cart.getId()).orElse(null);
        assertNotNull(savedCart);
        assertEquals(user, savedCart.getUser());
    }

}



