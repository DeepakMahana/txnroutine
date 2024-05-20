package com.pismo.txnroutine.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pismo.txnroutine.dto.request.AccountRequest;
import com.pismo.txnroutine.dto.response.AccountResponse;
import com.pismo.txnroutine.entity.Account;
import com.pismo.txnroutine.service.AccountService;
import com.pismo.txnroutine.util.MapperUtility;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest accountRequest) throws Exception {
        return ResponseEntity.ok(MapperUtility.convertClass(accountService.createAccount(accountRequest), AccountResponse.class));
    }
    
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable("accountId") Long accountId) {
        Optional<Account> optionalAccount = accountService.getAccount(accountId);
        if (optionalAccount.isPresent()) {
            AccountResponse accResp = MapperUtility.convertClass(optionalAccount.get(), AccountResponse.class);
            return ResponseEntity.ok(accResp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

