package com.example.foodexpress.service.impl;


import com.example.foodexpress.domain.dtos.model.UserRoleModel;
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
import java.util.NoSuchElementException;
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
    if(isDbInit()){
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


  public List<UserRoleModel> findAllRoles() {
    return this.userRoleRepository.findAll()
        .stream()
        .map(r -> this.modelMapper.map(r, UserRoleModel.class))
        .collect(Collectors.toList());
  }

  public UserRoleEntity findRoleByRoleName(UserRoleEnum admin) {
    return  this.userRoleRepository.findByRole(admin).get(0);
  }
}