package com.example.foodexpress.domain.dtos.banding;

public class UsersRestDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String address;


    public UsersRestDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UsersRestDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UsersRestDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UsersRestDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UsersRestDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UsersRestDTO setAddress(String address) {
        this.address = address;
        return this;
    }
}
