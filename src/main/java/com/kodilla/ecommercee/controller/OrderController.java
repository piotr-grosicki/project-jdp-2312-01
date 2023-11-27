package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.service.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @GetMapping()
    public List<OrderDto> getOrders() {
        return Arrays.asList(
                new OrderDto(1L, "Dummy Order One", "Description One"),
                new OrderDto(2L, "Dummy Order Two", "Description Two"),
                new OrderDto(3L, "Dummy Order Three", "Description Three")
        );
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(new OrderDto(1L, "Newly Created Dummy Order", "New Description"));
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Long orderId) {
        return new OrderDto(orderId, "Dummy Order", "Dummy Description");
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("orderId") Long orderId, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(new OrderDto(orderId, "Updated Dummy Order", "Updated Description"));
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok("Order with ID " + orderId + " deleted successfully");
    }
}
