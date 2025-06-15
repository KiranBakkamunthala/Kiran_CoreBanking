package com.example.core.service;

import com.example.core.entity.Account;

public interface AccountService {
    Account createAccount(Long customerId, String accountType);
    Account deposit(Long accountNumber, Double amount);
    Account withdraw(Long accountNumber, Double amount);
    Account closeAccount(Long accountNumber);
    Account getAccount(Long accountNumber);
}
