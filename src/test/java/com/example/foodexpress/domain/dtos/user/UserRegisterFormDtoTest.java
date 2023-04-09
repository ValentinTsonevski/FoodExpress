package com.example.foodexpress.domain.dtos.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterFormDtoTest {

    @Test
    public void testUserRegisterFormDto() {
        UserRegisterFormDto userRegisterFormDto = new UserRegisterFormDto()
                .setFirstName("John")
                .setLastName("Doe")
                .setUsername("johndoe")
                .setEmail("johndoe@example.com")
                .setPassword("password")
                .setConfirmPassword("password")
                .setAddress("123 Main St");

        assertEquals("John", userRegisterFormDto.getFirstName());
        assertEquals("Doe", userRegisterFormDto.getLastName());
        assertEquals("johndoe", userRegisterFormDto.getUsername());
        assertEquals("johndoe@example.com", userRegisterFormDto.getEmail());
        assertEquals("password", userRegisterFormDto.getPassword());
        assertEquals("password", userRegisterFormDto.getConfirmPassword());
        assertEquals("123 Main St", userRegisterFormDto.getAddress());
    }

}