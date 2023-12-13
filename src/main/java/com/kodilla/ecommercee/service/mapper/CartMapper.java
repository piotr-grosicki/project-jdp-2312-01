package com.kodilla.ecommercee.service.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.service.dto.CartDto;

public class CartMapper {
    public static CartDto mapToCartDto(Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getUser(),
                cart.getProducts()
        );
    }
    public static Cart mapToCart(CartDto cartDto) {
        return new Cart(
                cartDto.getId(),
                cartDto.getUser(),
                cartDto.getProducts()
        );
    }
}
