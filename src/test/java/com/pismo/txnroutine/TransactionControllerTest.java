package com.pismo.txnroutine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.txnroutine.controller.TransactionController;
import com.pismo.txnroutine.dto.request.TransactionRequest;
import com.pismo.txnroutine.entity.Transaction;
import com.pismo.txnroutine.service.TransactionService;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    void createTransactionTest() throws Exception {
    
        TransactionRequest txnRequest = TransactionRequest.builder()
                                                        .accountId(1L)
                                                        .operationTypeId(1L)
                                                        .amount(100.0)
                                                        .build();
        Transaction savedTransaction = Transaction.builder()
                                                  .id(1L)
                                                  .accountId(txnRequest.getAccountId())
                                                  .operationTypeId(txnRequest.getOperationTypeId())
                                                  .amount(txnRequest.getAmount())
                                                  .build();
        ArgumentCaptor<TransactionRequest> captor = ArgumentCaptor.forClass(TransactionRequest.class);

        when(transactionService.createTransaction(any())).thenReturn(savedTransaction);

        mockMvc.perform(post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(txnRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account_id").value(1L))
                .andExpect(jsonPath("$.operationtype_id").value(1L));

        verify(transactionService, times(1)).createTransaction(captor.capture());
        TransactionRequest capturedRequest = captor.getValue();
        assertEquals(txnRequest.getAmount(), capturedRequest.getAmount());
    }
}
