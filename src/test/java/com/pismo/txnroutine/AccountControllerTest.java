package com.pismo.txnroutine;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.txnroutine.controller.AccountController;
import com.pismo.txnroutine.dto.request.AccountRequest;
import com.pismo.txnroutine.entity.Account;
import com.pismo.txnroutine.repository.AccountRepository;
import com.pismo.txnroutine.service.AccountService;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    @DisplayName("should create an account")
    void shouldCreateAccount() throws Exception {

        // Given
        AccountRequest accountRequest = AccountRequest.builder().documentNumber("123456").build();
        Account acc = Account.builder().id(1L).documentNumber("123456").build();

        // Stubbing repository method
        when(accountRepository.findByDocumentNumber(eq("123456"))).thenReturn(Optional.empty());
        // Stubbing service method
        when(accountService.createAccount(any(AccountRequest.class))).thenReturn(acc);

        // When and Then
        mockMvc.perform(post("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(accountRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L)) 
                .andExpect(jsonPath("$.documentNumber").value("123456"));

        // Verify that the createAccount method of AccountService is called with the specified AccountRequest
        verify(accountService).createAccount(eq(accountRequest));
    }
    

    @Test
    @DisplayName("should fetch an account by id")
    void shouldFetchAccountById() throws Exception {
        // Given
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
