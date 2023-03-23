package com.example.foodexpress.service.impl;

import com.example.foodexpress.domain.dtos.banding.UserRegisterFormDto;
import com.example.foodexpress.domain.entity.UserEntity;
import com.example.foodexpress.domain.enums.UserRoleEnum;
import com.example.foodexpress.repository.UserRepository;
import com.example.foodexpress.repository.UserRoleRepository;
import com.example.foodexpress.service.DataBaseInitService;
import com.example.foodexpress.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, DataBaseInitService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void registerUser(UserRegisterFormDto userRegister) {
        if (isDbInit()) {
            dbInit();
        }
        UserEntity userEntity = new UserEntity()
                .setFirstName(userRegister.getFirstName())
                .setLastName(userRegister.getLastName())
                .setEmail(userRegister.getEmail())
                .setUsername(userRegister.getUsername())
                .setPassword(passwordEncoder.encode(userRegister.getPassword()))
                .setRoles(this.userRoleRepository.findByRole(UserRoleEnum.USER))
                .setAddress(userRegister.getAddress());

        this.userRepository.saveAndFlush(userEntity);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
      return this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("email not found"));
    }

    @Override
    public void dbInit() {
        UserEntity admin = new UserEntity().
                setFirstName("Admin").
                setLastName("Adminov").
                setEmail("Admin@example.com").
                setUsername("Admin21").
                setPassword(passwordEncoder.encode("123456")).
                setRoles(userRoleRepository.findAll()).
                setAddress("Admin Street 21");

        this.userRepository.saveAndFlush(admin);
    }

    @Override
    public boolean isDbInit() {
        return this.userRepository.count() == 0;
    }
}
