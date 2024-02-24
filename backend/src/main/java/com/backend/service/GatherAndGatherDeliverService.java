package com.backend.service;

import com.backend.constant.Constant;
import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.dto.confirm.DeliverConfirmByGatherEmployeeDTO;
import com.backend.dto.create.GatherAndGatherDeliverCreateDTO;
import com.backend.exception.NotExistException;
import com.backend.model.GatherAndGatherDeliver;
import com.backend.model.GatherEmployee;
import com.backend.model.GatherPointLeader;
import com.backend.model.PackageBox;
import com.backend.repository.GatherAndGatherDeliverRepository;
import com.backend.repository.GatherEmployeeRepository;
import com.backend.repository.GatherPointLeaderRepository;
import com.backend.repository.PackageBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GatherAndGatherDeliverService {
    @Autowired
    GatherAndGatherDeliverRepository gatherAndGatherDeliverRepository;

    @Autowired
    PackageBoxRepository packageBoxRepository;

    @Autowired
    GatherPointLeaderRepository gatherPointLeaderRepository;

    @Autowired
    GatherEmployeeRepository gatherEmployeeRepository;

    public List<GatherAndGatherDeliver> getAllReceiveGatherAndGatherDeliverList(GatherPointLeader receiveGatherPoint) {
        return gatherAndGatherDeliverRepository.findAllByReceiveGatherPoint(receiveGatherPoint);
    }

    public void createGatherAndGatherDeliver(GatherAndGatherDeliverCreateDTO createDTO) throws NotExistException {
        GatherEmployee sendGatherEmployee = gatherEmployeeRepository.findByUsername(createDTO.getSendGatherEmployeeUsername());
        if (Objects.isNull(sendGatherEmployee))
            throw new NotExistException(Message.notExist(EntityType.GATHER_EMPLOYEE, createDTO.getSendGatherEmployeeUsername()));

        GatherPointLeader receiveGatherPoint = gatherPointLeaderRepository.findByUsername(createDTO.getReceiveGatherPointUsername());
        if (Objects.isNull(receiveGatherPoint))
            throw new NotExistException(Message.notExist(EntityType.GATHER_POINT_LEADER, createDTO.getReceiveGatherPointUsername()));

        Optional<PackageBox> packageBox = packageBoxRepository.findById(createDTO.getPackageBoxId());
        if (packageBox.isEmpty())
            throw new NotExistException(Message.notExist(EntityType.PACKAGE_BOX, createDTO.getPackageBoxId().toString()));

        GatherAndGatherDeliver gatherAndGatherDeliver = new GatherAndGatherDeliver(sendGatherEmployee.getGatherPointLeader(), receiveGatherPoint, createDTO.getState(), new Timestamp(System.currentTimeMillis()), null, packageBox.get());
        gatherAndGatherDeliverRepository.save(gatherAndGatherDeliver);
    }

    public void confirm(DeliverConfirmByGatherEmployeeDTO confirmDTO) throws NotExistException {
        Optional<GatherAndGatherDeliver> optionalGatherAndGatherDeliver = gatherAndGatherDeliverRepository.findById(confirmDTO.getId());
        if (optionalGatherAndGatherDeliver.isEmpty()) {
            throw new NotExistException(Message.notExist(EntityType.GATHER_AND_GATHER, confirmDTO.getId().toString()));
        }

        GatherAndGatherDeliver gatherAndGatherDeliver = optionalGatherAndGatherDeliver.get();
        gatherAndGatherDeliver.setState(confirmDTO.getDeliverState());
        gatherAndGatherDeliver.setReceiveTime(new Timestamp(System.currentTimeMillis()));

        gatherAndGatherDeliverRepository.save(gatherAndGatherDeliver);
    }
}
