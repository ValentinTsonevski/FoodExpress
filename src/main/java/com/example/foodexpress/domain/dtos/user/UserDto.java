package com.example.foodexpress.domain.dtos.user;

import com.example.foodexpress.domain.dtos.cart.CartDto;

import java.util.List;

public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

    private String address;

    private List<UserRoleModelDto> roles;

    private CartDto cart;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public UserDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public List<UserRoleModelDto> getRoles() {
        return roles;
    }

    public UserDto setRoles(List<UserRoleModelDto> roles) {
        this.roles = roles;
        return this;
    }

    public CartDto getCart() {
        return cart;
    }

    public UserDto setCart(CartDto cart) {
        this.cart = cart;
        return this;
    }
}
