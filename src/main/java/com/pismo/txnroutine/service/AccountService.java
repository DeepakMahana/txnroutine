package com.pismo.txnroutine.service;

import com.pismo.txnroutine.model.Account;
import com.pismo.txnroutine.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(String documentNumber) {
        Account account = new Account();
        account.setDocumentNumber(documentNumber);
        return accountRepository.save(account);
    }

    public Optional<Account> getAccount(Long accountId) {
        return accountRepository.findById(accountId);
    }
}
