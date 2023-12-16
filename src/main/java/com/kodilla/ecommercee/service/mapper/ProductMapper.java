package com.kodilla.ecommercee.service.mapper;


import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.service.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductMapper {

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getProductName(),
                product.getPrice(),
                mapToIds(product.getCarts()),
                mapToIds(product.getOrders()),
                product.getGroup().getId()

        );
    }

    public Product mapToProduct(ProductDto productDto) {
        Product product = new Product(
                productDto.getId(),
                productDto.getProductName(),
                productDto.getPrice(),
                Collections.emptyList(),
                Collections.emptyList(),
                null
        );

        // Set the group based on the provided groupId
        Group group = new Group();
        group.setId(productDto.getGroupId());
        product.setGroup(group);

        return product;
    }

    private List<Long> mapToIds(List<?> entities) {
        return entities.stream()
                .map(entity -> {
                    if (entity instanceof Cart) {
                        return ((Cart) entity).getId();
                    } else if (entity instanceof Order) {
                        return ((Order) entity).getId();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }
}
