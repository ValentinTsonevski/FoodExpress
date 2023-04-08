package com.example.foodexpress.service;

import com.example.foodexpress.domain.dtos.user.UserDto;
import com.example.foodexpress.domain.dtos.user.UserRegisterFormDto;
import com.example.foodexpress.domain.dtos.user.UsersRestDto;
import com.example.foodexpress.domain.dtos.user.AllUsersViewDto;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void registerUser(UserRegisterFormDto userRegisterFormDto);

    UserDto getUserByEmail(String email);

    List<AllUsersViewDto> getAllUsers();

    Optional<UserDto> findUserByUsername(String username);


    void saveUser(UserDto user);

    void deleteUserREST(Long id) throws ObjectNotFoundException;

     List<UsersRestDto> getAllUsersRest();


}
