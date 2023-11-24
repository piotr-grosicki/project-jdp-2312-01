package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.model.entity.Product;
import com.kodilla.ecommercee.model.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements com.kodilla.ecommercee.service.ProductService {

    private final ProductRepository productRepository;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(long id) {
        Optional<Product> tempProduct = productRepository.findById(id);
        Product product;

        if (tempProduct.isPresent()) {
            product = tempProduct.get();
        } else {
            throw new RuntimeException("There is no Product for id: " + id);
        }

        return product;
    }

    @Override
    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> tempProduct = productRepository.findById(product.getId());
        Product finalProduct;

        if (tempProduct.isPresent()) {
            finalProduct = tempProduct.get();
        } else {
            throw new RuntimeException("There is no Product.id for id: " + product.getId());
        }

        return productRepository.save(finalProduct);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
