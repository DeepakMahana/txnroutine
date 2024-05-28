package com.pismo.txnroutine.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllTransactionDetailsResponse {

    private Long accountId;
    private String documentNumber;
    private Long operationTypeId;
    private String operationDescription;
    private Long transactionId;
    private Double amount;
    private LocalDateTime eventDate;
    
}
