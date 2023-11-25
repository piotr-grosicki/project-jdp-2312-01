package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.Cart;
import com.kodilla.ecommercee.service.dto.CartDto;
import com.kodilla.ecommercee.service.mapper.CartMapper;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Override
    public CartDto createEmptyCart() {
        Cart emptyCart = new Cart();
        return cartMapper.mapToCartDto(cartRepository.save(emptyCart));
    }

    @Override
    public CartDto getCartById(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with ID: " + cartId));
        return cartMapper.mapToCartDto(cart)
                ;
    }

    @Override
    public void addProductToCart(Long cartId, ProductDto productDto) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with ID: " + cartId));
        cartRepository.save(cart)
        ;
    }

    @Override
    public void removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with ID: " + cartId));
        cartRepository.save(cart)
        ;
    }

    @Override
    public void createOrderFromCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with ID: " + cartId));
        cartRepository.save(cart)
        ;
    }
}