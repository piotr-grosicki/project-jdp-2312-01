package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.model.repository.ProductRepository;
import com.kodilla.ecommercee.service.dto.ProductDto;
import com.kodilla.ecommercee.service.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::mapToProductDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("There is no Product for id: " + id));

        return productMapper.mapToProductDto(existingProduct);
    }

    public ProductDto createProduct(ProductDto productDto) {
        return productMapper.mapToProductDto(productRepository.save(productMapper.mapToProduct(productDto)));
    }

    public ProductDto updateProduct(ProductDto productDto) {
        Product existingProduct = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new ProductNotFoundException("There is no Product for id: " + productDto.getId()));

        existingProduct.setProductName(productDto.getProductName());
        existingProduct.setPrice(productDto.getPrice());

        Group group = new Group();
        group.setId(productDto.getGroupId());
        existingProduct.setGroup(group);

        return productMapper.mapToProductDto(productRepository.save(existingProduct));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
