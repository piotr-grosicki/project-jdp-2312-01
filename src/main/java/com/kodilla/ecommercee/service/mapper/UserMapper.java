package com.kodilla.ecommercee.service.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.model.repository.CartRepository;
import com.kodilla.ecommercee.service.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {

    private final CartRepository cartRepository;

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getCart().getId(),
                mapToIds(user.getOrders())

        );
    }

    public User mapToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());

        if (userDto.getCartId() != null) {
            Cart cart = cartRepository.findById(userDto.getCartId())
                    .orElseThrow(() -> new CartNotFoundException("Cart not found" + userDto.getCartId()));
            user.setCart(cart);
        }

        return user;
    }

    private List<Long> mapToIds(List<?> entities) {
        return entities.stream()
                .map(entity -> {
                    if (entity instanceof Order) {
                        return ((Order) entity).getId();
                    } else {
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }

}
