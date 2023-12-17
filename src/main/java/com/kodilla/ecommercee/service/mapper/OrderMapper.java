package com.kodilla.ecommercee.service.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.ProductService;
import com.kodilla.ecommercee.service.dto.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderMapper {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getOrderName(),
                order.getProducts().stream()
                        .map(Product::getId)
                        .collect(Collectors.toList()),
                order.getUser().getId()
        );
    }

    public Order mapToOrder(OrderDto orderDto) throws OrderNotFoundException {
        User user = new User();
        user.setId(orderDto.getUserId());

        return new Order(
                orderDto.getId(),
                orderDto.getOrderName(),
                orderDto.getProductsIds().stream()
                        .map(this::mapToProduct)
                        .collect(Collectors.toList()),
                user
        );
    }

    private Product mapToProduct(Long productDtoId) {
        Product product = productMapper.mapToProduct(productService.getProductById(productDtoId));
        if (product == null) {
            throw new ProductNotFoundException("There is no product for id: " + productDtoId);
        }
        return product;
    }

}
