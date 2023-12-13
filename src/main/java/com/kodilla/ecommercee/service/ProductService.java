package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.model.repository.ProductRepository;
import com.kodilla.ecommercee.service.dto.ProductDto;
import com.kodilla.ecommercee.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::mapToProductDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no Product for id: " + id));

        return ProductMapper.mapToProductDto(existingProduct);
    }

    public ProductDto createProduct(ProductDto productDto) {
        return ProductMapper.mapToProductDto(productRepository.save(ProductMapper.mapToProduct(productDto)));
    }

    public ProductDto updateProduct(ProductDto productDto) {
        Product existingProduct = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("There is no Product for id: " + productDto.getId()));

        return ProductMapper.mapToProductDto(productRepository.save(existingProduct));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}