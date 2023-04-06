package com.example.foodexpress.service;

import com.example.foodexpress.domain.dtos.banding.UserRegisterFormDto;
import com.example.foodexpress.domain.dtos.banding.UsersRestDTO;
import com.example.foodexpress.domain.dtos.view.AllUsersViewDto;
import com.example.foodexpress.domain.entity.UserEntity;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void registerUser(UserRegisterFormDto userRegisterFormDto);

    UserEntity getUserByEmail(String email);

    List<AllUsersViewDto> getAllUsers();

    Optional<UserEntity> findUserByUsername(String username);


    void saveUser(UserEntity user);

    void deleteUserREST(Long id) throws ObjectNotFoundException;

     List<UsersRestDTO> getAllUsersRest();


}
