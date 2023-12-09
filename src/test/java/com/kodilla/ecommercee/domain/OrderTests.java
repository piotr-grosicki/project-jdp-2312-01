package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.model.repository.OrderRepository;
import com.kodilla.ecommercee.model.repository.ProductRepository;
import com.kodilla.ecommercee.model.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class OrderTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    Order order = new Order();

    @BeforeEach
    public void initialize() {
        User user = new User();
        user.setId(1L);
        user.setUserName("Jonny");
        user = userRepository.save(user);

        Product product = new Product();
        product.setProductName("Product_one");
        product.setPrice(9999.99);
        product = productRepository.save(product);

        order.setId(1L);
        order.setUser(user);
        List<Product> productsList = new ArrayList<>();
        productsList.add(product);
        order.setProducts(productsList);
        order = orderRepository.save(order);
    }

    @Test
    void testSaveOrder() {
        // Given
        // When
        order = orderRepository.save(order);

        // Then
        assertEquals("Jonny", order.getUser().getUserName());
        assertEquals(1, order.getProducts().size());
    }

    @Test
    public void testFindOrderById() {
        // Given
        // When
        Order foundOrder = orderRepository.findById(order.getId()).orElseThrow(
                () -> new RuntimeException("There is no order for id: " + order.getId()));

        // Then
        assertNotNull(foundOrder);
        assertEquals("Jonny", order.getUser().getUserName());
        assertEquals(1, order.getProducts().size());
    }

    @Test
    public void updateOrder() {
        // Given
        // When
        order.getUser().setUserName("Bill");
        Order updatedOrder = orderRepository.save(order);

        // Then
        assertEquals("Bill", updatedOrder.getUser().getUserName());
    }

    @Test
    public void deleteOrderShouldNotDeleteUser() {
        // Given
        // When
        String userName = order.getUser().getUserName();
        orderRepository.deleteById(order.getId());

        // Then
        assertEquals("Jonny", userName);

    }
}
