package com.example.foodexpress.service.impl;


import com.example.foodexpress.domain.dtos.user.UserDto;
import com.example.foodexpress.domain.entity.UserEntity;
import com.example.foodexpress.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;



    @Test
    public void testIsDbInitShouldReturnTrueWhenUserRepositoryIsEmpty() {
        when(userRepository.count()).thenReturn(0L);

        boolean result = userService.isDbInit();

        assertTrue(result);
    }

    @Test
    public void testIsDbInitShouldReturnFalseWhenUserRepositoryIsNotEmpty() {
        when(userRepository.count()).thenReturn(1L);

        boolean result = userService.isDbInit();

        assertFalse(result);
    }

    @Test
    public void testGetUserByEmailShouldReturnUserDtoWhenUserExistsWithEmail() {
        String email = "admin@example.com";

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail(email);
        userEntity.setFirstName("admin");
        userEntity.setLastName("adminov");
        userEntity.setUsername("admin");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(userEntity));
        UserDto expectedUserDto = new UserDto();
        when(modelMapper.map(userEntity, UserDto.class)).thenReturn(expectedUserDto);


        UserDto result = userService.getUserByEmail(email);

        assertNotNull(result);
        assertEquals(expectedUserDto, result);

        verify(userRepository).findByEmail(email);
    }

    @Test
    public void testGetUserByEmailShouldThrowUsernameNotFoundException() {
        String email = "something@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.getUserByEmail(email));
    }



}