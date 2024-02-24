package com.backend.repository;

import com.backend.model.GatherPointLeader;
import com.backend.model.TransactionAndGatherDeliver;
import com.backend.model.TransactionPointLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionAndGatherDeliverRepository extends JpaRepository<TransactionAndGatherDeliver, Integer> {
    List<TransactionAndGatherDeliver> findAllByGatherPointLeader(GatherPointLeader gatherPointLeader);

    List<TransactionAndGatherDeliver> findAllByTransactionPointLeader(TransactionPointLeader transactionPointLeader);

    List<TransactionAndGatherDeliver> findAllByState(String state);

    List<TransactionAndGatherDeliver> findAllByTransactionPointLeaderAndState(TransactionPointLeader transactionPointLeader, String state);

    List<TransactionAndGatherDeliver> findAllByGatherPointLeaderAndState(GatherPointLeader gatherPointLeader, String state);
}
