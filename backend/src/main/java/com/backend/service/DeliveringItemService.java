package com.backend.service;

import com.backend.constant.Constant;
import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.dto.GatherItemDTO;
import com.backend.dto.TransactionItemDTO;
import com.backend.exception.NotExistException;
import com.backend.model.*;
import com.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DeliveringItemService {
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

    public List<PackageBox> getTransactionSendItemList(String transactionPointLeaderUsername) throws NotExistException {
        TransactionPointLeader transactionPointLeader = transactionPointLeaderRepository.findByUsername(transactionPointLeaderUsername);
        if (Objects.isNull(transactionPointLeader)) {
            throw new NotExistException(Message.notExist(EntityType.TRANSACTION_POINT_LEADER, transactionPointLeaderUsername));
        }

        List<PackageBox> transactionSendPackageBoxList = new ArrayList<>();

        List<CustomerAndTransactionDeliver> customerAndTransactionDeliverList = customerAndTransactionDeliverRepository.findAllByTransactionPointLeaderAndState(transactionPointLeader, Constant.onDeliverTransactionToCustomer);
        List<TransactionAndGatherDeliver> transactionAndGatherDeliverList = transactionAndGatherDeliverRepository.findAllByTransactionPointLeaderAndState(transactionPointLeader, Constant.onDeliverTransactionToGather);

        for (CustomerAndTransactionDeliver customerAndTransactionDeliver : customerAndTransactionDeliverList) {
            transactionSendPackageBoxList.add(customerAndTransactionDeliver.getPackageBox());
        }

        for (TransactionAndGatherDeliver transactionAndGatherDeliver : transactionAndGatherDeliverList) {
            transactionSendPackageBoxList.add(transactionAndGatherDeliver.getPackageBox());
        }

        return transactionSendPackageBoxList;
    }

    public List<PackageBox> getTransactionReceiveItemList(String transactionPointLeaderUsername) throws NotExistException {
        TransactionPointLeader transactionPointLeader = transactionPointLeaderRepository.findByUsername(transactionPointLeaderUsername);
        if (Objects.isNull(transactionPointLeader)) {
            throw new NotExistException(Message.notExist(EntityType.TRANSACTION_POINT_LEADER, transactionPointLeaderUsername));
        }

        List<PackageBox> transactionReceivePackageBoxList = new ArrayList<>();

        List<CustomerAndTransactionDeliver> customerAndTransactionDeliverList = customerAndTransactionDeliverRepository.findAllByTransactionPointLeaderAndState(transactionPointLeader, Constant.onDeliverCustomerToTransaction);
        List<TransactionAndGatherDeliver> transactionAndGatherDeliverList = transactionAndGatherDeliverRepository.findAllByTransactionPointLeaderAndState(transactionPointLeader, Constant.onDeliverGatherToTransaction);

        for (CustomerAndTransactionDeliver customerAndTransactionDeliver : customerAndTransactionDeliverList) {
            transactionReceivePackageBoxList.add(customerAndTransactionDeliver.getPackageBox());
        }

        for (TransactionAndGatherDeliver transactionAndGatherDeliver : transactionAndGatherDeliverList) {
            transactionReceivePackageBoxList.add(transactionAndGatherDeliver.getPackageBox());
        }

        return transactionReceivePackageBoxList;
    }

    public List<PackageBox> getGatherSendItemList(String gatherPointLeaderUsername) throws NotExistException {
        GatherPointLeader gatherPointLeader = gatherPointLeaderRepository.findByUsername(gatherPointLeaderUsername);
        if (Objects.isNull(gatherPointLeader)) {
            throw new NotExistException(Message.notExist(EntityType.GATHER_POINT_LEADER, gatherPointLeaderUsername));
        }

        List<PackageBox> gatherSendPackageBoxList = new ArrayList<>();

        List<TransactionAndGatherDeliver> transactionAndGatherDeliverList = transactionAndGatherDeliverRepository.findAllByGatherPointLeaderAndState(gatherPointLeader, Constant.onDeliverGatherToTransaction);
        List<GatherAndGatherDeliver> gatherAndGatherDeliverList = gatherAndGatherDeliverRepository.findAllBySendGatherPointAndState(gatherPointLeader, Constant.onDeliverToOtherGather);

        for (TransactionAndGatherDeliver transactionAndGatherDeliver : transactionAndGatherDeliverList) {
            gatherSendPackageBoxList.add(transactionAndGatherDeliver.getPackageBox());
        }

        for (GatherAndGatherDeliver gatherAndGatherDeliver : gatherAndGatherDeliverList) {
            gatherSendPackageBoxList.add(gatherAndGatherDeliver.getPackageBox());
        }

        return gatherSendPackageBoxList;
    }

    public List<PackageBox> getGatherReceiveItemList(String gatherPointLeaderUsername) throws NotExistException {
        GatherPointLeader gatherPointLeader = gatherPointLeaderRepository.findByUsername(gatherPointLeaderUsername);
        if (Objects.isNull(gatherPointLeader)) {
            throw new NotExistException(Message.notExist(EntityType.GATHER_POINT_LEADER, gatherPointLeaderUsername));
        }

        List<PackageBox> gatherReceivePackageBoxList = new ArrayList<>();

        List<TransactionAndGatherDeliver> transactionAndGatherDeliverList = transactionAndGatherDeliverRepository.findAllByGatherPointLeaderAndState(gatherPointLeader, Constant.onDeliverTransactionToGather);
        List<GatherAndGatherDeliver> gatherAndGatherDeliverList = gatherAndGatherDeliverRepository.findAllByReceiveGatherPointAndState(gatherPointLeader, Constant.onDeliverToOtherGather);

        for (TransactionAndGatherDeliver transactionAndGatherDeliver : transactionAndGatherDeliverList) {
            gatherReceivePackageBoxList.add(transactionAndGatherDeliver.getPackageBox());
        }

        for (GatherAndGatherDeliver gatherAndGatherDeliver : gatherAndGatherDeliverList) {
            gatherReceivePackageBoxList.add(gatherAndGatherDeliver.getPackageBox());
        }

        return gatherReceivePackageBoxList;
    }

    public List<GatherItemDTO> getAllGatherSendItemList() throws NotExistException {
        List<GatherItemDTO> gatherItemDTOList = new ArrayList<>();

        List<GatherPointLeader> gatherPointLeaderList = gatherPointLeaderRepository.findAll();

        for (GatherPointLeader gatherPointLeader : gatherPointLeaderList) {
            List<PackageBox> sendPackageBoxList = getGatherSendItemList(gatherPointLeader.getUsername());

            for (PackageBox packageBox : sendPackageBoxList) {
                gatherItemDTOList.add(new GatherItemDTO(packageBox, gatherPointLeader.getUsername()));
            }
        }

        return gatherItemDTOList;
    }

    public List<GatherItemDTO> getAllGatherReceiveItemList() throws NotExistException {
        List<GatherItemDTO> gatherItemDTOList = new ArrayList<>();

        List<GatherPointLeader> gatherPointLeaderList = gatherPointLeaderRepository.findAll();

        for (GatherPointLeader gatherPointLeader : gatherPointLeaderList) {
            List<PackageBox> receivePackageBoxList = getGatherReceiveItemList(gatherPointLeader.getUsername());

            for (PackageBox packageBox : receivePackageBoxList) {
                gatherItemDTOList.add(new GatherItemDTO(packageBox, gatherPointLeader.getUsername()));
            }
        }

        return gatherItemDTOList;
    }

    public List<TransactionItemDTO> getAllTransactionSendItemList() throws NotExistException {
        List<TransactionItemDTO> transactionItemDTOList = new ArrayList<>();

        List<TransactionPointLeader> transactionPointLeaderList = transactionPointLeaderRepository.findAll();

        for (TransactionPointLeader transactionPointLeader : transactionPointLeaderList) {
            List<PackageBox> sendPackageBoxList = getTransactionSendItemList(transactionPointLeader.getUsername());

            for (PackageBox packageBox : sendPackageBoxList) {
                transactionItemDTOList.add(new TransactionItemDTO(packageBox, transactionPointLeader.getUsername()));
            }
        }

        return transactionItemDTOList;
    }

    public List<TransactionItemDTO> getAllTransactionReceiveItemList() throws NotExistException {
        List<TransactionItemDTO> transactionItemDTOList = new ArrayList<>();

        List<TransactionPointLeader> transactionPointLeaderList = transactionPointLeaderRepository.findAll();

        for (TransactionPointLeader TransactionPointLeader : transactionPointLeaderList) {
            List<PackageBox> receivePackageBoxList = getTransactionReceiveItemList(TransactionPointLeader.getUsername());

            for (PackageBox packageBox : receivePackageBoxList) {
                transactionItemDTOList.add(new TransactionItemDTO(packageBox, TransactionPointLeader.getUsername()));
            }
        }

        return transactionItemDTOList;
    }

}
