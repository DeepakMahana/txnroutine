package com.pismo.txnroutine.controller;

import com.pismo.txnroutine.dto.AccountRequest;
import com.pismo.txnroutine.model.Account;
import com.pismo.txnroutine.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest account) {
        return ResponseEntity.ok(accountService.createAccount(account.getDocumentNumber()));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable("accountId") Long accountId) {
        return accountService.getAccount(accountId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}

