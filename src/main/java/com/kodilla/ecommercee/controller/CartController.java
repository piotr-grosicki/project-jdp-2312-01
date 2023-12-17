package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping()
    public ResponseEntity<List<CartDto>> getAllCarts() {
        try {
            return new ResponseEntity<>(cartService.getAllCarts(), HttpStatus.OK);

        } catch (CartNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(cartService.getCartById(id), HttpStatus.FOUND);
        } catch (CartNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto) {
        return new ResponseEntity<>(cartService.createCart(cartDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDto> updateCart(@PathVariable("id") Long id, @RequestBody CartDto cartDto) {
        cartDto.setId(id);
        try {
            return new ResponseEntity<>(cartService.updateCart(cartDto), HttpStatus.OK);
        } catch (CartNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") Long id) throws CartNotFoundException{
        try {
            cartService.deleteCart(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
