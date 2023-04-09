package com.example.foodexpress.domain.dtos.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AllUsersViewDtoTest {


    private AllUsersViewDto allUsersViewDto;

    @BeforeEach
    public void setUp() {
        allUsersViewDto = new AllUsersViewDto();
    }

    @Test
    public void testSetAndGetId() {
        Long id = 1L;
        allUsersViewDto.setId(id);
        assertEquals(id, allUsersViewDto.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        String username = "john";
        allUsersViewDto.setUsername(username);
        assertEquals(username, allUsersViewDto.getUsername());
    }

    @Test
    public void testSetAndGetRoles() {
        List<UserRoleModelDto> roles = Arrays.asList(
                new UserRoleModelDto(),
                new UserRoleModelDto()
        );
        allUsersViewDto.setRoles(roles);
        assertEquals(roles, allUsersViewDto.getRoles());
    }
}