package com.kodilla.ecommercee.service.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.service.dto.UserDto;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getCart(),
                user.getOrders()
        );
    }

    public static User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getCart(),
                userDto.getOrders()
        );
    }

}
