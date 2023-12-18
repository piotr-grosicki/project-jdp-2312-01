package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
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
    private final OrderMapper orderMapper;

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }

    public OrderDto getOrderById(Long id) throws OrderNotFoundException {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("There is no Order for id: " + id));

        return orderMapper.mapToOrderDto(existingOrder);
    }

    public OrderDto createOrder(OrderDto orderDto) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderRepository.save(orderMapper.mapToOrder(orderDto)));
    }

    public OrderDto updateOrder(Long id, OrderDto orderDto) throws OrderNotFoundException {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("There is no Order for id: " + id));

        Order updatedOrder = orderMapper.mapToOrder(orderDto);
        updatedOrder.setId(existingOrder.getId());

        return orderMapper.mapToOrderDto(orderRepository.save(updatedOrder));
    }

    public void deleteOrder(Long id) throws OrderNotFoundException {
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            throw new OrderNotFoundException("There is no Order for id: " + id);
        }
    }

}
