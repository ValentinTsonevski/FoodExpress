package com.example.foodexpress.domain.entity;

import com.example.foodexpress.domain.enums.UserRoleEnum;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.example.foodexpress.domain.enums.UserRoleEnum.ADMIN;
import static com.example.foodexpress.domain.enums.UserRoleEnum.USER;
import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    @Test
    public void testGettersAndSetters() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("jdoe");
        user.setEmail("jdoe@example.com");
        user.setPassword("password");
        user.setAddress("123 Main St");
        CartEntity cart = new CartEntity();
        user.setCart(cart);

        UserRoleEntity role = new UserRoleEntity();
        role.setRole(USER);
        user.setRoles(Collections.singletonList(role));

        assertEquals(1L, user.getId().longValue());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("jdoe", user.getUsername());
        assertEquals("jdoe@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("123 Main St", user.getAddress());
        assertEquals(cart, user.getCart());
        assertEquals(role, user.getRoles().get(0));
    }

    @Test
    public void testSetRoles() {
        UserEntity user = new UserEntity();
        UserRoleEntity role1 = new UserRoleEntity();
        role1.setRole(USER);
        UserRoleEntity role2 = new UserRoleEntity();
        role2.setRole(ADMIN);

        user.setRoles(Arrays.asList(role1, role2));
        assertEquals(2, user.getRoles().size());
        assertTrue(user.getRoles().contains(role1));
        assertTrue(user.getRoles().contains(role2));

        user.setRoles(Collections.singletonList(role1));
        assertEquals(1, user.getRoles().size());
        assertTrue(user.getRoles().contains(role1));

        user.setRoles(null);
        assertNull(user.getRoles());
    }

    @Test
    public void testSetCart() {
        UserEntity user = new UserEntity();
        CartEntity cart1 = new CartEntity();
        user.setCart(cart1);
        assertEquals(cart1, user.getCart());

        CartEntity cart2 = new CartEntity();
        user.setCart(cart2);
        assertEquals(cart2, user.getCart());

        user.setCart(null);
        assertNull(user.getCart());
    }


}