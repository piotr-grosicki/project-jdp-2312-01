package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.model.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProduct(long id);

    Product saveProduct(Product product);

    Product updateProduct(Product product);

    void deleteById(Long id);

}
