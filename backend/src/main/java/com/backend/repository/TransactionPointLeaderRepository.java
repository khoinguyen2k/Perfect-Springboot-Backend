package com.backend.repository;

import com.backend.model.GatherPointLeader;
import com.backend.model.TransactionPointLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionPointLeaderRepository extends JpaRepository<TransactionPointLeader, String> {
    TransactionPointLeader findByUsername(String username);

    List<TransactionPointLeader> findAllByGatherPointLeader(GatherPointLeader gatherPointLeader);
}
