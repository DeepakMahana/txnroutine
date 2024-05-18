package com.pismo.txnroutine.repository;

import com.pismo.txnroutine.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

