package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.service.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/cart")
@RequiredArgsConstructor

public class CartController {
    private List<Long> cartProduct = new ArrayList<>();

    @PostMapping(value = "/create")
    public void createCart(CartDto cartDto) {

    }
    @GetMapping("/{cartId}")
    public List<CartDto> getCart(@PathVariable Long cartId) {

        return new ArrayList<>();
    }
    @GetMapping("/{cartId}/all")
    public List<CartDto> getAllCartProduct(@PathVariable Long cartId) {
        List<CartDto> cartProduct = new ArrayList<>();
        cartProduct.add(new CartDto(1L, "Product 1", "Description 1"));
        cartProduct.add(new CartDto(2L, "Product 2", "Description 2"));
        return cartProduct;
    }
    @DeleteMapping("/{cartId}/{productId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long cartId, @PathVariable Long productId) {
        cartProduct.remove(productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{cartId}/addToCart")
    public ResponseEntity<Void> addToCart(@PathVariable Long cartId, @RequestParam Long productId) {
        cartProduct.add(productId);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{cartId}/createOrder")
    public ResponseEntity<String> createOrder(@PathVariable Long cartId) {
        String orderMessage = "Order created based on the items in the cart.";
        cartProduct.clear();
        return ResponseEntity.ok(orderMessage);
    }
}
