package com.example.foodexpress.domain.dtos.banding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserRegisterFormDto {
    @Size(min = 3,max = 25)
    private String firstName;

    @Size(min = 3,max = 25)
    private String lastName;

    @Size(min = 3,max = 25)
    private String username;

    @Email
    private String email;

    @Size(min = 5,max = 20)
    private String password;

    @Size(min = 5,max = 20)
    private String confirmPassword;

    @Size(min = 5)
    private String address;

    public UserRegisterFormDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterFormDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterFormDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterFormDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterFormDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterFormDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterFormDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserRegisterFormDto setAddress(String address) {
        this.address = address;
        return this;
    }
}
