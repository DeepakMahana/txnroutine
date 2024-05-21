package com.pismo.txnroutine.controller;

import com.pismo.txnroutine.dto.request.TransactionRequest;
import com.pismo.txnroutine.dto.response.AccountResponse;
import com.pismo.txnroutine.dto.response.TransactionResponse;
import com.pismo.txnroutine.entity.Transaction;
import com.pismo.txnroutine.service.TransactionService;
import com.pismo.txnroutine.util.MapperUtility;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@Valid @RequestBody TransactionRequest txnRequest) {
        return ResponseEntity.ok(MapperUtility.convertClass(transactionService.createTransaction(txnRequest), TransactionResponse.class));
    }
}

