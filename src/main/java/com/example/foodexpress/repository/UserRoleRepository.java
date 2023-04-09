package com.example.foodexpress.repository;

import com.example.foodexpress.domain.entity.UserRoleEntity;
import com.example.foodexpress.domain.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Long> {
   List<UserRoleEntity> findByRole(UserRoleEnum userRoleEnum);
}
