package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    @PostMapping("/create")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return new UserDto(1L, "User created");
    }

    @PutMapping("/block/{userId}")
    public UserDto blockUser(@PathVariable("userId") Long userId) {
        return new UserDto(userId, "User blocked");
    }

    @GetMapping("/generate-key/{userId}")
    public String generateKey(@PathVariable("userId") Long userId) {
        return "Generated key valid for one hour";
    }
}
