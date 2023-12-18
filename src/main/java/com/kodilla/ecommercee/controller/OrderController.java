package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("orderId") Long orderId) {
        try {
            return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws OrderNotFoundException {
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("orderId") Long orderId, @RequestBody OrderDto orderDto) {
        try {
            return new ResponseEntity<>(orderService.updateOrder(orderId, orderDto), HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Long orderId) throws OrderNotFoundException {
        try {
            orderService.deleteOrder(orderId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
