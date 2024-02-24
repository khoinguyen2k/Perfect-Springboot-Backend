package com.backend.service;

import com.backend.exception.NotExistException;
import com.backend.model.GatherPointLeader;
import com.backend.model.PackageBox;
import com.backend.model.TransactionPointLeader;
import com.backend.repository.GatherPointLeaderRepository;
import com.backend.repository.PackageBoxRepository;
import com.backend.repository.TransactionPointLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PackageBoxService {
    @Autowired
    PackageBoxRepository packageBoxRepository;

    @Autowired
    TransactionPointLeaderRepository transactionPointLeaderRepository;

    @Autowired
    GatherPointLeaderRepository gatherPointLeaderRepository;

    public List<PackageBox> getAllPackageBoxList() {
        return packageBoxRepository.findAll();
    }

    public void addItemByTransactionPointLeader(PackageBox packageBox, String transactionPointLeaderUsername) throws NotExistException {
        /**
         * MIDDLEWARE check item's attributes HERE!!
         */
        TransactionPointLeader transactionPointLeader = transactionPointLeaderRepository.findByUsername(transactionPointLeaderUsername);
        if (Objects.isNull(transactionPointLeader)) {
            throw new NotExistException("There's no transaction point leader with username " + transactionPointLeaderUsername + "!");
        }

        packageBoxRepository.save(packageBox);
    }

    public void addItemByGatherPointLeader(PackageBox packageBox, String gatherPointLeaderUsername) throws NotExistException {
        /**
         * MIDDLEWARE check item's attributes HERE!!
         */
        GatherPointLeader gatherPointLeader = gatherPointLeaderRepository.findByUsername(gatherPointLeaderUsername);
        if (Objects.isNull(gatherPointLeader)) {
            throw new NotExistException("There's no gather point leader with username " + gatherPointLeaderUsername + "!");
        }

        packageBoxRepository.save(packageBox);
    }
}
