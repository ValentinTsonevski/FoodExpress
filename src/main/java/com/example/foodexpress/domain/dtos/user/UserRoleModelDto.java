package com.example.foodexpress.domain.dtos.user;

public class UserRoleModelDto {

    private Long id;
    private String role;

    public UserRoleModelDto() {
    }

    public Long getId() {
        return id;
    }

    public UserRoleModelDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserRoleModelDto setRole(String role) {
        this.role = role;
        return this;
    }
}
