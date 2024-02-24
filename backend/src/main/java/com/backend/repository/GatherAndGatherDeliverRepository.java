package com.backend.repository;

import com.backend.model.GatherAndGatherDeliver;
import com.backend.model.GatherPointLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatherAndGatherDeliverRepository extends JpaRepository<GatherAndGatherDeliver, Integer> {
    List<GatherAndGatherDeliver> findAllBySendGatherPoint(GatherPointLeader sendGatherPoint);

    List<GatherAndGatherDeliver> findAllByReceiveGatherPoint(GatherPointLeader receiveGatherPoint);

    List<GatherAndGatherDeliver> findAllByState(String state);

    List<GatherAndGatherDeliver> findAllBySendGatherPointAndState(GatherPointLeader sendGatherPoint, String state);

    List<GatherAndGatherDeliver> findAllByReceiveGatherPointAndState(GatherPointLeader receiveGatherPoint, String state);
}
