package com.pismo.txnroutine.repository;

import com.pismo.txnroutine.dto.response.AllTransactionDetailsResponse;
import com.pismo.txnroutine.entity.Transaction;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT new com.pismo.txnroutine.dto.response.AllTransactionDetailsResponse(t.accountId, a.documentNumber, t.operationTypeId, o.description, t.id, t.amount, t.eventDate) " +
           "FROM Transaction t " +
           "JOIN Account a ON t.accountId = a.id " +
           "JOIN OperationType o ON t.operationTypeId = o.id " +
           "WHERE LOWER(a.documentNumber) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(o.description) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(CAST(t.amount AS string)) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<AllTransactionDetailsResponse> findBySearch(@Param("search") String search, Pageable pageable);

    @Query("SELECT * from transactions WHERE operationTypeId <> 4 AND balance > 0 Order By eventDate")
    List<Transaction> findByOperationIdForDebitTransactions();

    @Query("UPDATE transactions set balance = 0.0 where id = :id")
    void updateTransactionByIdWithZeroBalance(@Param("id") Long id);

    @Query("UPDATE transactions set balance = :balance where id = :id")
    void updateTransactionByIdWithPendingBalance(@Param("balance") double balance, @Param("id") Long id);
}
