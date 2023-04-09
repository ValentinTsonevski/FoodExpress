package com.example.foodexpress.domain.dtos.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileViewTest {
    @Test
    public void testUserProfileViewGettersAndSetters() {
        UserProfileView userProfileView = new UserProfileView();

        Long id = 1L;
        String username = "john_doe";
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@example.com";
        String address = "123 Main St";

        userProfileView.setId(id);
        userProfileView.setUsername(username);
        userProfileView.setFirstName(firstName);
        userProfileView.setLastName(lastName);
        userProfileView.setEmail(email);
        userProfileView.setAddress(address);

        assertEquals(id, userProfileView.getId());
        assertEquals(username, userProfileView.getUsername());
        assertEquals(firstName, userProfileView.getFirstName());
        assertEquals(lastName, userProfileView.getLastName());
        assertEquals(email, userProfileView.getEmail());
        assertEquals(address, userProfileView.getAddress());
    }

}