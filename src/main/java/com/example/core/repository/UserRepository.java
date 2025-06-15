package com.example.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.core.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}