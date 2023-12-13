package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.model.repository.CartRepository;
import com.kodilla.ecommercee.service.dto.CartDto;
import com.kodilla.ecommercee.service.mapper.CartMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public List<CartDto> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(CartMapper::mapToCartDto)
                .collect(Collectors.toList());
    }

    public CartDto getCartById(Long id) {
        Cart existingCart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no Cart for id: " + id));

        return CartMapper.mapToCartDto(existingCart);
    }

    public CartDto saveCart(CartDto cartDto) {
        return CartMapper.mapToCartDto(cartRepository.save(CartMapper.mapToCart(cartDto)));
    }

    public CartDto updateCart(CartDto cartDto) {
        Cart existingCart = cartRepository.findById(cartDto.getId())
                .orElseThrow(() -> new RuntimeException("There is no Cart for id: " + cartDto.getId()));

        existingCart.setProducts(cartDto.getProducts());
        existingCart.setUser(cartDto.getUser());
        return CartMapper.mapToCartDto(cartRepository.save(existingCart));
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

}
