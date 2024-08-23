package com.example.foodexpress.domain.dtos.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleModelDtoTest {

    @Test
    public void testUserRoleModelDto() {
        UserRoleModelDto userRoleModelDto = new UserRoleModelDto()
                .setId(1L)
                .setRole("ROLE_USER");

        assertNotNull(userRoleModelDto);
        assertEquals(1L, userRoleModelDto.getId());
        assertEquals("ROLE_USER", userRoleModelDto.getRole());
    }
}