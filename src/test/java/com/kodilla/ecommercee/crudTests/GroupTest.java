package com.kodilla.ecommercee.crudTests;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.model.repository.GroupRepository;
import com.kodilla.ecommercee.model.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class GroupTest {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveGroupWithProducts() {
        // Given
        Group group = new Group();
        group.setProductGroupName("Test Group");

        Product product1 = new Product();
        product1.setProductName("Product 1");
        Product product2 = new Product();
        product2.setProductName("Product 2");

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        group.setProducts(products);

        // When
        Group savedGroup = groupRepository.save(group);

        // Then
        assertNotNull(savedGroup.getId());
        assertEquals("Test Group", savedGroup.getProductGroupName());
        assertEquals(2, savedGroup.getProducts().size());
    }

    @Test
    public void testFindGroupWithProductsById() {
        // Given
        Group group = new Group();
        group.setProductGroupName("Test Group");

        Product product1 = new Product();
        product1.setProductName("Product 1");
        Product product2 = new Product();
        product2.setProductName("Product 2");

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        group.setProducts(products);

        Group savedGroup = groupRepository.save(group);

        // When
        Optional<Group> foundGroupOptional = groupRepository.findById(savedGroup.getId());

        // Then
        assertTrue(foundGroupOptional.isPresent());
        Group foundGroup = foundGroupOptional.get();
        assertEquals("Test Group", foundGroup.getProductGroupName());
        assertEquals(2, foundGroup.getProducts().size());
    }
    @Test
    public void testDeleteGroupShouldNotDeleteProducts() {
        // Given
        Group group = new Group(null, "Test Group", Collections.emptyList());
        group = groupRepository.save(group);

        Product product1 = new Product(null, "Test Product 1", 100.0, Collections.emptyList(), null, group);
        Product product2 = new Product(null, "Test Product 2", 200.0, Collections.emptyList(), null, group);
        product1 = productRepository.save(product1);
        product2 = productRepository.save(product2);

        // When
        groupRepository.deleteById(group.getId());

        // Then
        List<Product> productsAfterDelete = productRepository.findAll();
        assertEquals(2, productsAfterDelete.size());
    }
}
