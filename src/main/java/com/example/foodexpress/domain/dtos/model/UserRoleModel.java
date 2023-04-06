package com.example.foodexpress.domain.dtos.model;

public class UserRoleModel {

    private Long id;
    private String role;

    public UserRoleModel() {
    }

    public Long getId() {
        return id;
    }

    public UserRoleModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserRoleModel setRole(String role) {
        this.role = role;
        return this;
    }
}
