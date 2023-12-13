package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.dto.CartDto;
import com.kodilla.ecommercee.service.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;


    @GetMapping()
    public ResponseEntity<List<CartDto>> getAllCarts() {
        return new ResponseEntity<>(cartService.getAllCarts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(cartService.getCartById(id), HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto) {
        return new ResponseEntity<>(cartService.saveCart(cartDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDto> updateCart(@PathVariable("id") Long id, @RequestBody CartDto cartDto) {
        cartDto.setId(id);
        return new ResponseEntity<>(cartService.updateCart(cartDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable("id") Long id) {
        cartService.deleteCart(id);
    }
}