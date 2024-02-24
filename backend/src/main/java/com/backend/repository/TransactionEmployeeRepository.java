package com.backend.repository;

import com.backend.model.TransactionEmployee;
import com.backend.model.TransactionPointLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionEmployeeRepository extends JpaRepository<TransactionEmployee, String> {
    TransactionEmployee findByUsername(String username);
    List<TransactionEmployee> findAllByTransactionPointLeader(TransactionPointLeader transactionPointLeader);
}
