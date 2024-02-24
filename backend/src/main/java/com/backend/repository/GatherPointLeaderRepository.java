package com.backend.repository;

import com.backend.model.GatherPointLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatherPointLeaderRepository extends JpaRepository<GatherPointLeader, String> {
    GatherPointLeader findByUsername(String username);
}
