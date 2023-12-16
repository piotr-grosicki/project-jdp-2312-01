package com.kodilla.ecommercee.crudTests;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.model.repository.CartRepository;
import com.kodilla.ecommercee.model.repository.ProductRepository;
import com.kodilla.ecommercee.model.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindUserById() {
        // Given
        User user = new User(null, "TestUser", null, null);
        userRepository.save(user);

        // When
        Optional<User> foundUser = userRepository.findById(user.getId());

        // Then
        User retrievedUser = foundUser.orElseThrow(() -> new RuntimeException("User not found"));

        assertEquals("TestUser", retrievedUser.getUserName());
    }

    @Test
    public void testUpdateUser() {
        // Given
        User user = new User(null, "TestUser", null, null);
        userRepository.save(user);

        // When
        user.setUserName("UpdatedUser");
        userRepository.save(user);
        Optional<User> updatedUser = userRepository.findById(user.getId());

        // Then
        User retrievedUser = updatedUser.orElseThrow(() -> new RuntimeException("User not found"));
        assertEquals("UpdatedUser", retrievedUser.getUserName());
    }

    @Test
    public void testDeleteUser() {
        // Given
        User user = new User(null, "TestUser", null, null);
        userRepository.save(user);

        // When
        userRepository.deleteById(user.getId());
        Optional<User> deletedUser = userRepository.findById(user.getId());

        // Then
        assertFalse(deletedUser.isPresent());
    }

    @Test
    public void deleteCartShouldNotDeleteProductAndUser() {
        // Given
        Product product1 = new Product(1L, "Product 1", 999.99, null, null, null);
        productRepository.save(product1);
        List<Product> products = new ArrayList<>();
        products.add(product1);

        User user = new User(null, "TestUser", null, null);
        userRepository.save(user);

        Cart cart = new Cart(1L, user, products);
        cartRepository.save(cart);

        // When
        cartRepository.deleteById(1L);

        // Then
        assertEquals(1, userRepository.count());
        assertEquals(1, productRepository.count());
        assertEquals("Product 1", cart.getProducts().get(0).getProductName());
        assertEquals("TestUser", cart.getUser().getUserName());

    }

    @AfterEach
    public void cleanUp() {
        cartRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();
    }

}
