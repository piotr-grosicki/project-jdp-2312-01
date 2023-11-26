package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.service.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrders() {
        return new ArrayList<>();
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) {
        return new OrderDto(1L, "ORD123", "IN_PROGRESS");
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{orderId}")
    public OrderDto updateOrder(@PathVariable Long orderId, @RequestBody OrderDto orderDto) {
        return new OrderDto(orderId, orderDto.getOrderNumber(), orderDto.getStatus());
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok().build();
    }
}