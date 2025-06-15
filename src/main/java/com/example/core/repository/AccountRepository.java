package com.example.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.core.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {}
