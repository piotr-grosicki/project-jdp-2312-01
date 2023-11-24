package com.kodilla.ecommercee.service.mapper;

import com.kodilla.ecommercee.model.entity.Product;
import com.kodilla.ecommercee.service.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    public Product mapToProduct(ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getValue()
        );
    }

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getValue()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
