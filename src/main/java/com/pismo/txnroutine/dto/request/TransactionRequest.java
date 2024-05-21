package com.pismo.txnroutine.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TransactionRequest {

    @NotEmpty(message = "Account Id must not be null or empty")
    @JsonProperty("account_id")
    private Long accountId;

    @NotEmpty(message = "OperationType ID must not be null or empty")
    @JsonProperty("operation_type_id")
    private Long operationTypeId;

    @NotEmpty(message = "Amount must not be null or empty")
    @JsonProperty("amount")
    private Double amount;
}
