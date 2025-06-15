package com.example.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.core.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
