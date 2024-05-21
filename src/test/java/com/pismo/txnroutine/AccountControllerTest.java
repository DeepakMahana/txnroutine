package com.pismo.txnroutine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.txnroutine.controller.AccountController;
import com.pismo.txnroutine.dto.request.AccountRequest;
import com.pismo.txnroutine.entity.Account;
import com.pismo.txnroutine.service.AccountService;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    @DisplayName("should create an account")
    void shouldCreateAccount() throws Exception {

        AccountRequest accountRequest = AccountRequest.builder().documentNumber("123456").build();
        Account acc = Account.builder().id(1L).documentNumber("123456").build();
        ArgumentCaptor<AccountRequest> captor = ArgumentCaptor.forClass(AccountRequest.class);

        when(accountService.createAccount(any())).thenReturn(acc);

        mockMvc.perform(post("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(accountRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.documentNumber").value("123456"));

        verify(accountService, times(1)).createAccount(captor.capture());
        AccountRequest capturedRequest = captor.getValue();
        assertEquals(accountRequest.getDocumentNumber(), capturedRequest.getDocumentNumber());
    }

    @Test
    @DisplayName("should fetch an account by id")
    void shouldFetchAccountById() throws Exception {
        
        long accountId = 1L;
        Account account = Account.builder().id(accountId).documentNumber("123456789").build();
        when(accountService.getAccount(accountId)).thenReturn(Optional.of(account));

        // When
        mockMvc.perform(get("/api/v1/accounts/{accountId}", accountId))
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(accountId))
                .andExpect(jsonPath("$.documentNumber").value("123456789"));

        // Verify that the getAccount method of AccountService is called with the specified accountId
        verify(accountService).getAccount(eq(accountId));
    }
}
