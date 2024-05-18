package com.pismo.txnroutine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OperationType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationTypeId;
    
    private String description;
}
