package com.backend.service;

import com.backend.constant.Constant;
import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.exception.NotExistException;
import com.backend.model.*;
import com.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StockItemService {
    @Autowired
    CustomerAndTransactionDeliverRepository customerAndTransactionDeliverRepository;

    @Autowired
    TransactionAndGatherDeliverRepository transactionAndGatherDeliverRepository;

    @Autowired
    GatherAndGatherDeliverRepository gatherAndGatherDeliverRepository;

    @Autowired
    TransactionPointLeaderRepository transactionPointLeaderRepository;

    @Autowired
    GatherPointLeaderRepository gatherPointLeaderRepository;

    public List<PackageBox> getTransactionStockItemList(String transactionPointLeaderUsername) throws NotExistException {
        TransactionPointLeader transactionPointLeader = transactionPointLeaderRepository.findByUsername(transactionPointLeaderUsername);
        if (Objects.isNull(transactionPointLeader)) {
            throw new NotExistException(Message.notExist(EntityType.TRANSACTION_POINT_LEADER, transactionPointLeaderUsername));
        }

        List<PackageBox> transactionStockPackageBoxList = new ArrayList<>();

        List<CustomerAndTransactionDeliver> customerAndTransactionStockDeliverList = customerAndTransactionDeliverRepository.findAllByTransactionPointLeaderAndState(transactionPointLeader, Constant.successfulTransactionStock);
        List<TransactionAndGatherDeliver> transactionAndGatherStockDeliverList = transactionAndGatherDeliverRepository.findAllByTransactionPointLeaderAndState(transactionPointLeader, Constant.successfulTransactionStock);

        for (CustomerAndTransactionDeliver customerAndTransactionDeliver : customerAndTransactionStockDeliverList) {
            transactionStockPackageBoxList.add(customerAndTransactionDeliver.getPackageBox());
        }

        for (TransactionAndGatherDeliver transactionAndGatherDeliver : transactionAndGatherStockDeliverList) {
            transactionStockPackageBoxList.add(transactionAndGatherDeliver.getPackageBox());
        }

        Set<PackageBox> packageBoxSet = new HashSet<>(transactionStockPackageBoxList);
        return new ArrayList<>(packageBoxSet);
    }

    public List<PackageBox> getGatherStockItemList(String gatherPointLeaderUsername) throws NotExistException {
        GatherPointLeader gatherPointLeader = gatherPointLeaderRepository.findByUsername(gatherPointLeaderUsername);
        if (Objects.isNull(gatherPointLeader)) {
            throw new NotExistException(Message.notExist(EntityType.GATHER_POINT_LEADER, gatherPointLeaderUsername));
        }

        List<PackageBox> gatherStockPackageBoxList = new ArrayList<>();

        List<TransactionAndGatherDeliver> transactionAndGatherStockDeliverList = transactionAndGatherDeliverRepository.findAllByGatherPointLeaderAndState(gatherPointLeader, Constant.successfulGatherStock);
        List<GatherAndGatherDeliver> sendGatherStockDeliverList = gatherAndGatherDeliverRepository.findAllBySendGatherPointAndState(gatherPointLeader, Constant.successfulGatherStock);
        List<GatherAndGatherDeliver> receiveGatherStockDeliverList = gatherAndGatherDeliverRepository.findAllByReceiveGatherPointAndState(gatherPointLeader, Constant.successfulGatherStock);

        for (TransactionAndGatherDeliver transactionAndGatherDeliver : transactionAndGatherStockDeliverList) {
            gatherStockPackageBoxList.add(transactionAndGatherDeliver.getPackageBox());
        }

        for (GatherAndGatherDeliver gatherAndGatherDeliver : sendGatherStockDeliverList) {
            gatherStockPackageBoxList.add(gatherAndGatherDeliver.getPackageBox());
        }

        for (GatherAndGatherDeliver gatherAndGatherDeliver : receiveGatherStockDeliverList) {
            gatherStockPackageBoxList.add(gatherAndGatherDeliver.getPackageBox());
        }

        Set<PackageBox> packageBoxSet = new HashSet<>(gatherStockPackageBoxList);
        return new ArrayList<>(packageBoxSet);
    }
}
