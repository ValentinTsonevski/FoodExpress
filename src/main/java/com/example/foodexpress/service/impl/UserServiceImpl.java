package com.example.foodexpress.service.impl;

import com.example.foodexpress.domain.dtos.user.*;
import com.example.foodexpress.domain.entity.UserEntity;
import com.example.foodexpress.domain.enums.UserRoleEnum;
import com.example.foodexpress.repository.UserRepository;
import com.example.foodexpress.repository.UserRoleRepository;
import com.example.foodexpress.service.DataBaseInitService;
import com.example.foodexpress.service.UserService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.foodexpress.outputs.ExceptionMessages.EMAIL_NOT_FOUND;
import static com.example.foodexpress.outputs.ExceptionMessages.USER_NOT_FOUND_BY_ID;

@Service
public class UserServiceImpl implements UserService, DataBaseInitService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder,ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
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
    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(EMAIL_NOT_FOUND));
      return   mapUserEntityToDto(userEntity);
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


    @Override
    public List<AllUsersViewDto> getAllUsers() {
        return
                this.userRepository.
                        findAll()
                        .stream()
                        .map(this::map).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findUserByUsername(String username) {
        return userRepository.findByUsername(username).map(this::mapUserEntityToDto);
    }

    @Override
    public void saveUser(UserDto userDto) {
        UserEntity userEntity = mapUserDtoToEntity(userDto);
        this.userRepository.saveAndFlush(userEntity);
    }


    private AllUsersViewDto map(UserEntity userEntity) {
        return new AllUsersViewDto()
                .setUsername(userEntity.getUsername())
                .setRoles(userEntity.getRoles()
                        .stream()
                        .map(roleEntity -> this.modelMapper.map(roleEntity, UserRoleModelDto.class))
                        .collect(Collectors.toList()));

    }

    @Override
    public void deleteUserREST(Long id) throws ObjectNotFoundException {

        UserEntity user = this.userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(USER_NOT_FOUND_BY_ID + id));
        this.userRepository.delete(user);
        this.userRepository.flush();
    }

    @Override
    public List<UsersRestDto> getAllUsersRest() {
        List<UserEntity> allUsers = this.userRepository.findAll().stream().toList();

        return allUsers.stream().map(user -> this.modelMapper.map(user, UsersRestDto.class)).toList();
    }


    private   UserDto mapUserEntityToDto(UserEntity userEntity){
     return  this.modelMapper.map(userEntity,UserDto.class);
    }
    private UserEntity mapUserDtoToEntity(UserDto userDto){
       return this.modelMapper.map(userDto,UserEntity.class);
    }


}
