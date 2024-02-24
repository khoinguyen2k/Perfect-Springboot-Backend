package com.backend.service;

import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.dto.confirm.DeliverConfirmByGatherEmployeeDTO;
import com.backend.dto.confirm.DeliverConfirmByTransactionEmployeeDTO;
import com.backend.dto.create.GatherAndTransactionDeliverCreateDTO;
import com.backend.dto.create.TransactionAndGatherDeliverCreateDTO;
import com.backend.exception.NotExistException;
import com.backend.model.*;
import com.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionAndGatherDeliverService {
    @Autowired
    TransactionAndGatherDeliverRepository transactionAndGatherDeliverRepository;

    @Autowired
    PackageBoxRepository packageBoxRepository;

    @Autowired
    TransactionPointLeaderRepository transactionPointLeaderRepository;

    @Autowired
    GatherPointLeaderRepository gatherPointLeaderRepository;

    @Autowired
    TransactionEmployeeRepository transactionEmployeeRepository;

    @Autowired
    GatherEmployeeRepository gatherEmployeeRepository;

    public List<TransactionAndGatherDeliver> getAllReceiveTransactionAndGatherDeliverList(GatherPointLeader receiveGatherPoint) {
        return transactionAndGatherDeliverRepository.findAllByGatherPointLeader(receiveGatherPoint);
    }

    public void createTransactionAndGatherDeliver(TransactionAndGatherDeliverCreateDTO createDTO) throws NotExistException {
        TransactionEmployee transactionEmployee = transactionEmployeeRepository.findByUsername(createDTO.getTransactionEmployeeUsername());
        if (Objects.isNull(transactionEmployee))
            throw new NotExistException(Message.notExist(EntityType.TRANSACTION_EMPLOYEE, createDTO.getTransactionEmployeeUsername()));

        GatherPointLeader gatherPointLeader = gatherPointLeaderRepository.findByUsername(createDTO.getGatherPointUsername());
        if (Objects.isNull(gatherPointLeader))
            throw new NotExistException(Message.notExist(EntityType.GATHER_POINT_LEADER, createDTO.getGatherPointUsername()));

        Optional<PackageBox> packageBox = packageBoxRepository.findById(createDTO.getPackageBoxId());
        if (packageBox.isEmpty())
            throw new NotExistException(Message.notExist(EntityType.PACKAGE_BOX, createDTO.getPackageBoxId().toString()));

        TransactionAndGatherDeliver transactionAndGatherDeliver = new TransactionAndGatherDeliver(gatherPointLeader, transactionEmployee.getTransactionPointLeader(), createDTO.getState(), new Timestamp(System.currentTimeMillis()), null, packageBox.get());
        transactionAndGatherDeliverRepository.save(transactionAndGatherDeliver);
    }

    public void createGatherAndTransactionDeliver(GatherAndTransactionDeliverCreateDTO createDTO) throws NotExistException {
        GatherEmployee gatherEmployee = gatherEmployeeRepository.findByUsername(createDTO.getGatherEmployeeUsername());
        if (Objects.isNull(gatherEmployee))
            throw new NotExistException(Message.notExist(EntityType.GATHER_EMPLOYEE, createDTO.getGatherEmployeeUsername()));

        TransactionPointLeader transactionPointLeader = transactionPointLeaderRepository.findByUsername(createDTO.getTransactionPointLeaderUsername());
        if (Objects.isNull(transactionPointLeader))
            throw new NotExistException(Message.notExist(EntityType.TRANSACTION_POINT_LEADER, createDTO.getTransactionPointLeaderUsername()));

        Optional<PackageBox> packageBox = packageBoxRepository.findById(createDTO.getPackageBoxId());
        if (packageBox.isEmpty())
            throw new NotExistException(Message.notExist(EntityType.PACKAGE_BOX, createDTO.getPackageBoxId().toString()));

        TransactionAndGatherDeliver transactionAndGatherDeliver = new TransactionAndGatherDeliver(gatherEmployee.getGatherPointLeader(), transactionPointLeader, createDTO.getState(), new Timestamp(System.currentTimeMillis()), null, packageBox.get());
        transactionAndGatherDeliverRepository.save(transactionAndGatherDeliver);
    }

    public void confirm(DeliverConfirmByGatherEmployeeDTO confirmDTO) throws NotExistException {
        Optional<TransactionAndGatherDeliver> optionalTransactionAndGatherDeliver = transactionAndGatherDeliverRepository.findById(confirmDTO.getId());
        if (optionalTransactionAndGatherDeliver.isEmpty()) {
            throw new NotExistException(Message.notExist(EntityType.TRANSACTION_AND_GATHER, confirmDTO.getId().toString()));
        }

        TransactionAndGatherDeliver transactionAndGatherDeliver = optionalTransactionAndGatherDeliver.get();
        transactionAndGatherDeliver.setState(confirmDTO.getDeliverState());
        transactionAndGatherDeliver.setReceiveTime(new Timestamp(System.currentTimeMillis()));

        transactionAndGatherDeliverRepository.save(transactionAndGatherDeliver);
    }

    public void confirm(DeliverConfirmByTransactionEmployeeDTO confirmDTO) throws NotExistException {
        Optional<TransactionAndGatherDeliver> optionalTransactionAndGatherDeliver = transactionAndGatherDeliverRepository.findById(confirmDTO.getId());
        if (optionalTransactionAndGatherDeliver.isEmpty()) {
            throw new NotExistException(Message.notExist(EntityType.TRANSACTION_AND_GATHER, confirmDTO.getId().toString()));
        }

        TransactionAndGatherDeliver transactionAndGatherDeliver = optionalTransactionAndGatherDeliver.get();
        transactionAndGatherDeliver.setState(confirmDTO.getDeliverState());
        transactionAndGatherDeliver.setReceiveTime(new Timestamp(System.currentTimeMillis()));

        transactionAndGatherDeliverRepository.save(transactionAndGatherDeliver);
    }

}
