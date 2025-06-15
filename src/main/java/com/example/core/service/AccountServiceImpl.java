package com.example.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.core.entity.Account;
import com.example.core.entity.Customer;
import com.example.core.exception.ResourceNotFoundException;
import com.example.core.repository.AccountRepository;
import com.example.core.repository.CustomerRepository;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Account createAccount(Long customerId, String accountType) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Account account = new Account();
        account.setCustomer(customer);
        account.setAccountType(accountType);
        account.setBalance(0.0);
        account.setStatus("Active");
        return accountRepository.save(account);
    }

    @Override
    public Account deposit(Long accountNumber, Double amount) {
    	logger.info("Initiating deposit of {} into account {}", amount, accountNumber);
        Account account = getAccount(accountNumber);
        account.setBalance(account.getBalance() + amount);
        logger.debug("New balance for account {}: {}", accountNumber, account.getBalance());
        return accountRepository.save(account);
    }

    @Override
    public Account withdraw(Long accountNumber, Double amount) {
    	logger.info("Initiating withdrawal of {} from account {}", amount, accountNumber);
        Account account = getAccount(accountNumber);
        if (account.getBalance() < amount) {
        	logger.warn("Insufficient balance in account {}. Available: {}, Requested: {}",
                    accountNumber, account.getBalance(), amount);
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }

    @Override
    public Account closeAccount(Long accountNumber) {
        Account account = getAccount(accountNumber);
        account.setStatus("Closed");
        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(Long accountNumber) {
        return accountRepository.findById(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with number: " + accountNumber));
    }
}
