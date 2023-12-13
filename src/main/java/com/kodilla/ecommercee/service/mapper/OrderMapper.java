package com.kodilla.ecommercee.service.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.service.dto.OrderDto;

public class OrderMapper {

    public static OrderDto mapToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setProductName(order.getOrderName());
        orderDto.setProductDescription(order.getProductDescription());

        return orderDto;
    }

    public static Order mapToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setOrderName(orderDto.getProductName());
        order.setProductDescription(orderDto.getProductDescription());

        return order;
    }
}
