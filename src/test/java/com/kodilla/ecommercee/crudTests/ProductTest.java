package com.kodilla.ecommercee.crudTests;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.model.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProduct() {
        // Given
        Product product = new Product(null, "Test Product", 50.0, null, null, null);

        // When
        productRepository.save(product);

        // Then
        assertNotNull(product.getId());
    }

    @Test
    public void testFindProductById() {
        // Given
        Product product = new Product(null, "Test Product", 50.0, null, null, null);
        productRepository.save(product);

        // When
        Optional<Product> foundProduct = productRepository.findById(product.getId());

        // Then
        assertAll("Product",
                () -> assertNotNull(foundProduct.orElse(null), "Product not found"),
                () -> assertEquals("Test Product", foundProduct.map(Product::getProductName).orElse(null)),
                () -> assertEquals(50.0, foundProduct.map(Product::getPrice).orElse(null), 0.001)
        );
    }

    @Test
    public void testUpdateProduct() {
        // Given
        Product product = new Product(null, "Test Product", 50.0, null, null, null);
        productRepository.save(product);

        // When
        product.setProductName("Updated Product");
        productRepository.save(product);

        // Then
        Optional<Product> updatedProduct = productRepository.findById(product.getId());
        Product retrievedProduct = updatedProduct.orElseGet(() -> {
            fail("Product not found");
            return null;
        });
        assertEquals("Updated Product", retrievedProduct.getProductName());
    }

    @Test
    public void testDeleteProduct() {
        // Given
        Product product = new Product(null, "Test Product", 50.0, null, null, null);
        productRepository.save(product);

        // When
        productRepository.deleteById(product.getId());

        // Then
        Optional<Product> deletedProduct = productRepository.findById(product.getId());
        assertFalse(deletedProduct.isPresent());
    }

    @Test
    public void testFindAllProducts() {
        // Given
        productRepository.saveAndFlush(new Product(null, "Product 1", 10.0, null, null, null));
        productRepository.saveAndFlush(new Product(null, "Product 2", 20.0, null, null, null));

        // When
        List<Product> allProducts = productRepository.findAll();

        // Then
        assertEquals(2, allProducts.size());
    }

    @Test
    public void testDeleteAllProducts() {
        // Given
        productRepository.saveAndFlush(new Product(null, "Product 1", 10.0, null, null, null));
        productRepository.saveAndFlush(new Product(null, "Product 2", 20.0, null, null, null));

        // When
        productRepository.deleteAll();

        // Then
        List<Product> remainingProducts = productRepository.findAll();
        assertEquals(0, remainingProducts.size());
    }
}