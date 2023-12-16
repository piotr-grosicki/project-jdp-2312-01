package com.kodilla.ecommercee.service.mapper;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.service.dto.CartDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CartMapper {
    public CartDto mapToCartDto(Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getUser().getId(),
                mapToIds(cart.getProducts())
        );
    }
    public Cart mapToCart(CartDto cartDto) {
        Cart cart = new Cart(
                cartDto.getId(),
                null,
                null
        );
        User user = new User();
        user.setId(cartDto.getUserId());
        cart.setUser(user);

        return cart;
    }
    private List<Long> mapToIds(List<?> entities) {
        return entities.stream()
                .map(entity -> {
                    if (entity instanceof Cart) {
                        return ((Cart) entity).getId();
                    } else if (entity instanceof Product) {
                        return ((Product) entity).getId();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }
}
