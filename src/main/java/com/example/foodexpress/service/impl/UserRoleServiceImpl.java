package com.example.foodexpress.service.impl;


import com.example.foodexpress.domain.dtos.user.UserRoleModelDto;
import com.example.foodexpress.domain.entity.UserRoleEntity;
import com.example.foodexpress.domain.enums.UserRoleEnum;
import com.example.foodexpress.repository.UserRoleRepository;
import com.example.foodexpress.service.DataBaseInitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Order(0)
@Service
public class UserRoleServiceImpl implements DataBaseInitService {

    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.dbInit();
    }


    @Override
    public void dbInit() {
        if (isDbInit()) {
            List<UserRoleEntity> roles = new ArrayList<>();

            roles.add(new UserRoleEntity().setRole(UserRoleEnum.USER));
            roles.add(new UserRoleEntity().setRole(UserRoleEnum.ADMIN));

            this.userRoleRepository.saveAllAndFlush(roles);
        }

    }

    @Override
    public boolean isDbInit() {
        return this.userRoleRepository.count() == 0;
    }



    public UserRoleModelDto findRoleByRoleName(UserRoleEnum admin) {
        UserRoleEntity userRoleEntity = this.userRoleRepository.findByRole(admin).get(0);
        return mapUserRoleEntityToDto(userRoleEntity);
    }

    private UserRoleModelDto mapUserRoleEntityToDto(UserRoleEntity userRoleEntity) {
        return this.modelMapper.map(userRoleEntity, UserRoleModelDto.class);
    }
}