package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.model.repository.OrderRepository;
import com.kodilla.ecommercee.service.dto.OrderDto;
import com.kodilla.ecommercee.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }

    public OrderDto getOrderById(Long id) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no Order for id: " + id));

        return OrderMapper.mapToOrderDto(existingOrder);
    }

    public OrderDto createOrder(OrderDto orderDto) {
        return OrderMapper.mapToOrderDto(orderRepository.save(OrderMapper.mapToOrder(orderDto)));
    }

    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no Order for id: " + id));

        Order updatedOrder = OrderMapper.mapToOrder(orderDto);
        updatedOrder.setId(existingOrder.getId());

        return OrderMapper.mapToOrderDto(orderRepository.save(updatedOrder));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
