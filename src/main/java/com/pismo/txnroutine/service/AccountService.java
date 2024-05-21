package com.pismo.txnroutine.service;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pismo.txnroutine.dto.request.AccountRequest;
import com.pismo.txnroutine.entity.Account;
import com.pismo.txnroutine.exceptions.ApiErrors;
import com.pismo.txnroutine.exceptions.ApplicationException;
import com.pismo.txnroutine.repository.AccountRepository;
import com.pismo.txnroutine.util.MapperUtility;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Account createAccount(AccountRequest accountRequest) {
        if (accountRepository.findByDocumentNumber(accountRequest.getDocumentNumber()).isPresent()) {
            logger.error("Document number {} already exists", accountRequest.getDocumentNumber());
            throw new ApplicationException(ApiErrors.DOCUMENT_NO_ALREADY_EXIST, accountRequest.getDocumentNumber());
        }
        Account savedAccount = accountRepository.save(Objects.requireNonNull(MapperUtility.convertClass(accountRequest, Account.class)));
        logger.info("Account created with document number {}", savedAccount.getDocumentNumber());
        return savedAccount;
    }

    public Optional<Account> getAccount(Long accountId) {
        return accountRepository.findById(accountId);
    }
}
