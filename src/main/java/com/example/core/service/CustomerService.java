package com.example.core.service;

import com.example.core.entity.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer getCustomer(Long id);
}
