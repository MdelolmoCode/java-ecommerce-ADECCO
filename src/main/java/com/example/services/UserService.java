package com.example.services;

import com.example.entities.UserEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findById(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    UserEntity save(UserEntity user);
    UserEntity update(UserEntity user);
}
