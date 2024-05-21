package com.pismo.txnroutine.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pismo.txnroutine.dto.request.TransactionRequest;
import com.pismo.txnroutine.entity.Transaction;
import com.pismo.txnroutine.exceptions.ApiErrors;
import com.pismo.txnroutine.exceptions.ApplicationException;
import com.pismo.txnroutine.repository.TransactionRepository;
import com.pismo.txnroutine.util.MapperUtility;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OperationTypeService operationTypeService;

    @Transactional
    public Transaction createTransaction(TransactionRequest txnRequest) {

        if (!accountService.getAccount(txnRequest.getAccountId()).isPresent()) {
            logger.error("Account ID : {} not found", txnRequest.getAccountId());
            throw new ApplicationException(ApiErrors.ACCOUNT_NOT_FOUND, txnRequest.getAccountId());
        }

        if (!operationTypeService.getOperationType(txnRequest.getOperationTypeId()).isPresent()) {
            logger.error("Operation Type with ID : {} not found", txnRequest.getOperationTypeId());
            throw new ApplicationException(ApiErrors.OPERATIONTYPE_NOT_FOUND, txnRequest.getOperationTypeId());
        }

        Transaction transaction = Transaction.builder()
                                             .accountId(txnRequest.getAccountId())
                                             .operationTypeId(txnRequest.getOperationTypeId())
                                             .amount(txnRequest.getAmount())
                                             .eventDate(LocalDateTime.now())
                                             .build();
        Transaction savedTransaction = transactionRepository.save(Objects.requireNonNull(MapperUtility.convertClass(transaction, Transaction.class)));
        return savedTransaction;
    }
}
