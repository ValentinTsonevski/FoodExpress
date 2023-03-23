package com.example.foodexpress.service;

import com.example.foodexpress.domain.dtos.banding.UserRegisterFormDto;
import com.example.foodexpress.domain.entity.UserEntity;

public interface UserService {
    void registerUser(UserRegisterFormDto userRegisterFormDto);

    UserEntity getUserByEmail(String email);
}
