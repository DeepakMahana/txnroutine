package com.pismo.txnroutine.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pismo.txnroutine.entity.OperationType;
import com.pismo.txnroutine.repository.OperationTypeRepository;

@Service
public class OperationTypeService {

    @Autowired
    private OperationTypeRepository operationTypeRepository;

    public Optional<OperationType> getOperationType(Long operationTypeId) {
        return operationTypeRepository.findById(operationTypeId);
    }
}
