package com.pismo.txnroutine.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "operation_type_id", nullable = false)
    private Long OperationTypeId;

    private Double amount;

    private LocalDateTime eventDate;
}
