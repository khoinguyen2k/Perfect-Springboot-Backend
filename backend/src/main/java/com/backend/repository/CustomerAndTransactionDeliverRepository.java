package com.backend.repository;

import com.backend.model.Customer;
import com.backend.model.CustomerAndTransactionDeliver;
import com.backend.model.TransactionPointLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAndTransactionDeliverRepository extends JpaRepository<CustomerAndTransactionDeliver, Integer> {
    List<CustomerAndTransactionDeliver> findAllByCustomer(Customer customer);

    List<CustomerAndTransactionDeliver> findAllByState(String state);

    List<CustomerAndTransactionDeliver> findAllByTransactionPointLeaderAndState(TransactionPointLeader transactionPointLeader, String state);
}
