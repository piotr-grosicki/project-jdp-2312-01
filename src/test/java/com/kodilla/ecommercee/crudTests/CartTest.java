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
    public void testSaveCart() {
        // Given
        User user = new User();
        user.setUserName("John");
        user = userRepository.save(user);

        Product product = new Product();
        product.setProductName("Product");
        product.setPrice(50.0);
        product = productRepository.save(product);

        Cart cart = new Cart();
        cart.setUser(user);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        cart.setProducts(productList);

        // When
        cart = cartRepository.save(cart);

        // Then
        assertNotNull(cart.getId());
    }
    @Test
    public void testFindCartById() {
        // Given
        User user = new User();
        user.setUserName("John");
        user = userRepository.save(user);

        Product product = new Product();
        product.setProductName("Product");
        product.setPrice(50.0);
        product = productRepository.save(product);

        Cart cart = new Cart();
        cart.setUser(user);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        cart.setProducts(productList);
        cart = cartRepository.save(cart);

        // When
        Cart foundCart = cartRepository.findById(cart.getId()).orElse(null);

        // Then
        assertNotNull(foundCart);
        assertEquals(user.getId(), foundCart.getUser().getId());
        assertEquals(1, foundCart.getProducts().size());
        assertEquals(product.getId(), foundCart.getProducts().get(0).getId());
    }

    @Test
    public void testUpdateCart() {
        // Given
        User user = new User();
        user.setUserName("John");
        user = userRepository.save(user);

        Product product = new Product();
        product.setProductName("Simple Product");
        product.setPrice(50.0);
        product = productRepository.save(product);

        Cart cart = new Cart();
        cart.setUser(user);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        cart.setProducts(productList);
        cart = cartRepository.save(cart);

        // When
        cart.getProducts().clear();
        cartRepository.save(cart);

        Cart updatedCart = cartRepository.findById(cart.getId()).orElse(null);

        // Then
        assertNotNull(updatedCart);
        assertEquals(0, updatedCart.getProducts().size());
    }

    @Test
    public void testDeleteCart() {
        // Given
        User user = new User();
        user.setUserName("John");
        user = userRepository.save(user);

        Product product = new Product();
        product.setProductName("Simple Product");
        product.setPrice(50.0);
        product = productRepository.save(product);

        Cart cart = new Cart();
        cart.setUser(user);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        cart.setProducts(productList);
        cart = cartRepository.save(cart);

        // When
        cartRepository.deleteById(cart.getId());

        // Then
        assertFalse(cartRepository.existsById(cart.getId()));
        assertEquals("John", cart.getUser().getUserName());
        assertEquals(1, userRepository.count());

    }
    @AfterEach
    public void cleanUp() {
        cartRepository.deleteAll();
        userRepository.deleteAll();
    }
}



