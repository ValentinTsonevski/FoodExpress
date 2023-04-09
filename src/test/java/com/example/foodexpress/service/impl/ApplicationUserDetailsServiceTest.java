package com.example.foodexpress.service.impl;

import com.example.foodexpress.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class ApplicationUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    @Mock
    private ApplicationUserDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new ApplicationUserDetailsService(
                mockUserRepo
        );
    }

    @Test
    void testLoadUserByUsernameUserDoesNotExist() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("notFound@example.com")
        );
    }


}