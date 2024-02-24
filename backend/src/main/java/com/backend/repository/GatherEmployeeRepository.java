package com.backend.repository;

import com.backend.model.GatherEmployee;
import com.backend.model.GatherPointLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatherEmployeeRepository extends JpaRepository<GatherEmployee, String> {
    GatherEmployee findByUsername(String username);
    List<GatherEmployee> findAllByGatherPointLeader(GatherPointLeader gatherPointLeader);
}
