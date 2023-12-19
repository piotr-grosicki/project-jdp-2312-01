package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
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

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User existingUser = userRepository.findById(id)

                .orElseThrow(() -> new UserNotFoundException("There is no User for id: " + id));

        return userMapper.mapToUserDto(existingUser);
    }

    public UserDto createUser(UserDto userDto) {
        return userMapper.mapToUserDto(userRepository.save(userMapper.mapToUser(userDto)));
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id)

                .orElseThrow(() -> new UserNotFoundException("There is no User for id: " + id));

        User updatedUser = userMapper.mapToUser(userDto);
        updatedUser.setId(existingUser.getId());

        return userMapper.mapToUserDto(userRepository.save(updatedUser));
    }

    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id)
            ;
        } catch (Exception e) {
            throw new UserNotFoundException("There is no User for id: " + id);
        }
    }

}

