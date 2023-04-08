package com.example.foodexpress.domain.dtos.user;

public class UsersRestDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String address;


    public UsersRestDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UsersRestDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UsersRestDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UsersRestDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UsersRestDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UsersRestDto setAddress(String address) {
        this.address = address;
        return this;
    }
}
