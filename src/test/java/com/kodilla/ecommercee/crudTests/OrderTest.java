package com.kodilla.ecommercee.crudTests;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.model.repository.OrderRepository;
import com.kodilla.ecommercee.model.repository.ProductRepository;
import com.kodilla.ecommercee.model.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class OrderTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testSaveOrder() {
        // Given
        User user = new User();
        user.setUserName("Jonny");
        user = userRepository.save(user);

        Product product1 = new Product(1L, "Milk", 2.99,
                Collections.emptyList(), Collections.emptyList(), null);
        Product product2 = new Product(2L, "Tea", 6.99,
                Collections.emptyList(), Collections.emptyList(), null);
        product1 = productRepository.save(product1);
        product2 = productRepository.save(product2);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Order order = new Order();
        order.setOrderName("2023-12-14-001");
        order.setUser(user);
        order.setProducts(products);

        // When
        order = orderRepository.save(order);

        // Then
        assertEquals("Jonny", order.getUser().getUserName());
        assertEquals("Milk", products.get(0).getProductName());
        assertEquals(2, order.getProducts().size());
    }

    @Test
    public void testFindOrderById() {
        // Given
        User user = new User();
        user.setUserName("Jonny");
        user = userRepository.save(user);

        Product product1 = new Product(1L, "Milk", 2.99,
                Collections.emptyList(), Collections.emptyList(), null);
        Product product2 = new Product(2L, "Tea", 6.99,
                Collections.emptyList(), Collections.emptyList(), null);
        product1 = productRepository.save(product1);
        product2 = productRepository.save(product2);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Order order = new Order();
        order.setId(1L);
        order.setUser(user);
        order.setProducts(products);
        order = orderRepository.save(order);

        // When
        Order foundOrder = orderRepository.findById(order.getId()).orElse(null);

        // Then
        assertNotNull(foundOrder);
        assertEquals("Jonny", foundOrder.getUser().getUserName());
        assertEquals(2, foundOrder.getProducts().size());
    }

    @Test
    public void updateOrder() {
        // Given
        User user = new User();
        user.setUserName("Jonny");
        user = userRepository.save(user);

        Product product1 = new Product(1L, "Milk", 2.99,
                Collections.emptyList(), Collections.emptyList(), null);
        Product product2 = new Product(2L, "Tea", 6.99,
                Collections.emptyList(), Collections.emptyList(), null);
        product1 = productRepository.save(product1);
        product2 = productRepository.save(product2);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Order order = new Order();
        order.setId(1L);
        order.setUser(user);
        order.setProducts(products);
        order = orderRepository.save(order);

        // When
        order.getUser().setUserName("Bill");
        order.getProducts().get(0).setProductName("Beer");
        order = orderRepository.save(order);

        // Then
        assertEquals("Bill", order.getUser().getUserName());
        assertEquals("Beer", order.getProducts().get(0).getProductName());
    }

    @Test
    public void deleteOrderShouldNotDeleteProducts() {
        // Given
        User user = new User();
        user.setUserName("Jonny");
        user = userRepository.save(user);

        Product product1 = new Product(1L, "Milk", 2.99,
                Collections.emptyList(), Collections.emptyList(), null);
        Product product2 = new Product(2L, "Tea", 6.99,
                Collections.emptyList(), Collections.emptyList(), null);
        product1 = productRepository.save(product1);
        product2 = productRepository.save(product2);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Order order = new Order();
        order.setId(1L);
        order.setUser(user);
        order.setProducts(products);
        order = orderRepository.save(order);

        // When
        orderRepository.deleteById(1L);

        // Then
        assertNotNull(products);
        assertEquals(2, products.size());
    }

    @AfterEach
    public void cleanUp() {
        productRepository.deleteAll();
        orderRepository.deleteAll();
        userRepository.deleteAll();
    }
}
