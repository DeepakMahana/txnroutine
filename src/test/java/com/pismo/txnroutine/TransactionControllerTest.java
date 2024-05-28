package com.pismo.txnroutine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.txnroutine.controller.TransactionController;
import com.pismo.txnroutine.dto.request.TransactionRequest;
import com.pismo.txnroutine.dto.response.AllTransactionDetailsResponse;
import com.pismo.txnroutine.entity.Transaction;
import com.pismo.txnroutine.exceptions.ApiErrors;
import com.pismo.txnroutine.exceptions.ApplicationException;
import com.pismo.txnroutine.service.TransactionService;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    @DisplayName("account not found while creating transaction")
    void createTransaction_AccountNotFound() throws Exception {
            TransactionRequest txnRequest = TransactionRequest.builder()
                    .accountId(1L)
                    .operationTypeId(1L)
                    .amount(100.0)
                    .build();

            when(transactionService.createTransaction(any())).thenThrow(new ApplicationException(ApiErrors.ACCOUNT_NOT_FOUND, 1L));

            mockMvc.perform(post("/api/v1/transactions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(txnRequest)))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.errorCode").value("ACC-002"));
        }

    @Test
    @DisplayName("create transaction")
    void createTransaction() throws Exception {
    
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

    @Test
    @DisplayName("fetch all transactions")
    void getAllTransactions() throws Exception {
       
        List<AllTransactionDetailsResponse> transactionsList = new ArrayList<>();
        transactionsList.add(new AllTransactionDetailsResponse(1L, "123456", 1L, "Debit", 1L, 300.0, LocalDateTime.now()));
        transactionsList.add(new AllTransactionDetailsResponse(2L, "123490", 2L, "Credit", 1L, 600.0, LocalDateTime.now()));
        Page<AllTransactionDetailsResponse> transactionsPage = new PageImpl<>(transactionsList);

        when(transactionService.getTransactionsPageable(anyString(), anyInt(), anyInt(), anyBoolean())).thenReturn(transactionsPage);

        mockMvc.perform(get("/api/v1/transactions/fetch-all")
                .param("search", "Credit")
                .param("page", "1")
                .param("size", "5")
                .param("descending", "false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].accountId").value(1L))
                .andExpect(jsonPath("$.content[0].documentNumber").value("123456"))
                .andExpect(jsonPath("$.content[0].operationTypeId").value(1L))
                .andExpect(jsonPath("$.content[0].operationDescription").value("Debit"))
                .andExpect(jsonPath("$.content[0].transactionId").value(1L))
                .andExpect(jsonPath("$.content[0].amount").value(300.0))
                .andExpect(jsonPath("$.content[0].eventDate").exists())
                .andExpect(jsonPath("$.currentPage").value(1))
                .andExpect(jsonPath("$.offset").value(2))
                .andExpect(jsonPath("$.count").value(2))
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.totalPages").value(1));

        verify(transactionService).getTransactionsPageable("Credit", 0, 5, false);
}

}
