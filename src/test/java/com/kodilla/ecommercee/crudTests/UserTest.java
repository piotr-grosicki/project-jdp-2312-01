package com.kodilla.ecommercee.crudTests;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.model.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserById() {
        // Given
        User user = new User(null, "TestUser", null, null);
        userRepository.save(user);

        // When
        Optional<User> foundUser = userRepository.findById(user.getId());

        // Then
        User retrievedUser = foundUser.orElseThrow(() -> new RuntimeException("User not found"));

        assertEquals("TestUser", retrievedUser.getUserName());
    }

    @Test
    public void testUpdateUser() {
        // Given
        User user = new User(null, "TestUser", null, null);
        userRepository.save(user);

        // When
        user.setUserName("UpdatedUser");
        userRepository.save(user);
        Optional<User> updatedUser = userRepository.findById(user.getId());

        // Then
        User retrievedUser = updatedUser.orElseThrow(() -> new RuntimeException("User not found"));
        assertEquals("UpdatedUser", retrievedUser.getUserName());
    }

    @Test
    public void testDeleteUser() {
        // Given
        User user = new User(null, "TestUser", null, null);
        userRepository.save(user);

        // When
        userRepository.deleteById(user.getId());
        Optional<User> deletedUser = userRepository.findById(user.getId());

        // Then
        assertFalse(deletedUser.isPresent());
    }
}
