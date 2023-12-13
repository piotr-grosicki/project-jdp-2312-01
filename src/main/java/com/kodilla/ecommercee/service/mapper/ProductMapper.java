package com.kodilla.ecommercee.service.mapper;


import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.service.dto.ProductDto;

public class ProductMapper {

    public static ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getProductName(),
                product.getPrice(),
                product.getCarts(),
                product.getOrders(),
                product.getGroup()
        );
    }

    public static Product mapToProduct(ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getProductName(),
                productDto.getPrice(),
                productDto.getCarts(),
                productDto.getOrders(),
                productDto.getGroup()
        );
    }
  
}