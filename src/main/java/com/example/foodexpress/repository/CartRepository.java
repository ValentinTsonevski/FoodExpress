package com.example.foodexpress.repository;

import com.example.foodexpress.domain.entity.CartEntity;

import com.example.foodexpress.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CartRepository extends JpaRepository<CartEntity,Long> {

    CartEntity findCartEntityByUser(UserEntity user);
}
