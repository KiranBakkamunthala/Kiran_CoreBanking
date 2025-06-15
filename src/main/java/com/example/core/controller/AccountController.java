package com.example.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.entity.Account;
import com.example.core.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

	/*
	 * @PostMapping public ResponseEntity<Account> createAccount(@RequestParam Long
	 * customerId,
	 * 
	 * @RequestParam String accountType) { return new
	 * ResponseEntity<>(accountService.createAccount(customerId, accountType),
	 * HttpStatus.CREATED); }
	 */
    
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestParam Long customerId,
                                                 @RequestParam String accountType) {
        Account created = accountService.createAccount(customerId, accountType);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long accountNumber,
                                           @RequestParam Double amount) {
        return ResponseEntity.ok(accountService.deposit(accountNumber, amount));
    }

    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Long accountNumber,
                                            @RequestParam Double amount) {
        return ResponseEntity.ok(accountService.withdraw(accountNumber, amount));
    }

    @PutMapping("/{accountNumber}/close")
    public ResponseEntity<Account> close(@PathVariable Long accountNumber) {
        return ResponseEntity.ok(accountService.closeAccount(accountNumber));
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountNumber) {
        return ResponseEntity.ok(accountService.getAccount(accountNumber));
    }
}