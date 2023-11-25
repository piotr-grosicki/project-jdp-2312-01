package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.dto.CartDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<CartDto> createEmptyCart() {
        return ResponseEntity.ok(cartService.createEmptyCart());
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.getCartById(cartId));
    }

    @PostMapping("/{cartId}/products/add")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long cartId, @RequestBody ProductDto productDto) {
        cartService.addProductToCart(cartId, productDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartId}/products/{productId}/remove")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.removeProductFromCart(cartId, productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{cartId}/order/create")
    public ResponseEntity<Void> createOrderFromCart(@PathVariable Long cartId) {
        cartService.createOrderFromCart(cartId);
        return ResponseEntity.ok().build();
    }
}