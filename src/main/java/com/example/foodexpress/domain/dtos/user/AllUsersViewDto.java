package com.example.foodexpress.domain.dtos.user;

import java.util.Collections;
import java.util.List;

public class AllUsersViewDto {
    private Long id;
    private String username;
    private List<UserRoleModelDto> roles;

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

    public List<UserRoleModelDto> getRoles() {
        return Collections.unmodifiableList(roles);
    }

    public AllUsersViewDto setRoles(List<UserRoleModelDto> roles) {
        this.roles = roles;
        return this;
    }
}
