package com.example.foodexpress.domain.dtos.view;

import com.example.foodexpress.domain.dtos.model.UserRoleModel;

import java.util.Collections;
import java.util.List;

public class AllUsersViewDto {
    private Long id;
    private String username;
    private List<UserRoleModel> roles;

    public AllUsersViewDto() {
    }

    public Long getId() {
        return id;
    }

    public AllUsersViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AllUsersViewDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<UserRoleModel> getRoles() {
        return Collections.unmodifiableList(roles);
    }

    public AllUsersViewDto setRoles(List<UserRoleModel> roles) {
        this.roles = roles;
        return this;
    }
}
