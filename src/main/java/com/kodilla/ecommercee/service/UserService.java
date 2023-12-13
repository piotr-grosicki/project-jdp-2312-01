package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.model.repository.UserRepository;
import com.kodilla.ecommercee.service.dto.UserDto;
import com.kodilla.ecommercee.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no User for id: " + id));

        return UserMapper.mapToUserDto(existingUser);
    }

    public UserDto saveUser(UserDto userDto) {
        return UserMapper.mapToUserDto(userRepository.save(UserMapper.mapToUser(userDto)));
    }

    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("There is no User for id: " + userDto.getId()));

        existingUser.setUserName(userDto.getUserName());
        existingUser.setCart(userDto.getCart());
        existingUser.setOrders(userDto.getOrders());

        return UserMapper.mapToUserDto(userRepository.save(existingUser));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
