package com.example.foodexpress.domain.dtos.user;

import com.example.foodexpress.domain.dtos.cart.CartDto;
import com.example.foodexpress.domain.dtos.offer.OfferDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    @Test
    public void testUserDtoGettersAndSetters() {
        UserDto dto = new UserDto();
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String username = "johndoe";
        String email = "johndoe@example.com";
        String password = "password";
        String address = "123 Main St";
        List<UserRoleModelDto> roles = Arrays.asList(new UserRoleModelDto(), new UserRoleModelDto());
        CartDto cart = new CartDto();
        dto.setId(id);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setUsername(username);
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setAddress(address);
        dto.setRoles(roles);
        dto.setCart(cart);
        assertEquals(id, dto.getId());
        assertEquals(firstName, dto.getFirstName());
        assertEquals(lastName, dto.getLastName());
        assertEquals(username, dto.getUsername());
        assertEquals(email, dto.getEmail());
        assertEquals(password, dto.getPassword());
        assertEquals(address, dto.getAddress());
        assertEquals(roles, dto.getRoles());
        assertEquals(cart, dto.getCart());
    }

}