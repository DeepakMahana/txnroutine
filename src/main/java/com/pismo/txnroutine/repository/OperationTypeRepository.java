package com.pismo.txnroutine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pismo.txnroutine.entity.OperationType;

@Repository
public interface OperationTypeRepository extends JpaRepository<OperationType, Long> {
}
