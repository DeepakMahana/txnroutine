package com.pismo.txnroutine.controller;

import com.pismo.txnroutine.dto.request.TransactionRequest;
import com.pismo.txnroutine.dto.response.AllTransactionDetailsResponse;
import com.pismo.txnroutine.dto.response.PagedResponse;
import com.pismo.txnroutine.dto.response.TransactionResponse;
import com.pismo.txnroutine.service.TransactionService;
import com.pismo.txnroutine.util.MapperUtility;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/fetch-all")
    public ResponseEntity<PagedResponse<AllTransactionDetailsResponse>> getAllTransactions(
        @RequestParam(defaultValue = "") String search,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "false") Boolean descending
    ) {
        Page<AllTransactionDetailsResponse> transactionsPage = transactionService.getTransactionsPageable(search, page - 1, size, descending);
        PagedResponse<AllTransactionDetailsResponse> response = MapperUtility.convertPageToList(transactionsPage, AllTransactionDetailsResponse.class);
        return ResponseEntity.ok(response);
    }
}

