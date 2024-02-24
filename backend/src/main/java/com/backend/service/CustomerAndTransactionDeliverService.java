package com.backend.service;

import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.dto.confirm.DeliverConfirmByTransactionEmployeeDTO;
import com.backend.dto.create.CustomerAndTransactionDeliverCreateDTO;
import com.backend.exception.NotExistException;
import com.backend.model.*;
import com.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerAndTransactionDeliverService {
    @Autowired
    CustomerAndTransactionDeliverRepository customerAndTransactionDeliverRepository;

    @Autowired
    PackageBoxRepository packageBoxRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TransactionPointLeaderRepository transactionPointLeaderRepository;

    @Autowired
    TransactionEmployeeRepository transactionEmployeeRepository;

    public void createCustomerAndTransactionDeliver(CustomerAndTransactionDeliverCreateDTO createDTO) throws NotExistException {
        Customer customer = customerRepository.findByUsername(createDTO.getCustomerUsername());
        if (Objects.isNull(customer))
            throw new NotExistException(Message.notExist(EntityType.CUSTOMER, createDTO.getCustomerUsername()));

        TransactionEmployee transactionEmployee = transactionEmployeeRepository.findByUsername(createDTO.getTransactionEmployeeUsername());
        if (Objects.isNull(transactionEmployee))
            throw new NotExistException(Message.notExist(EntityType.TRANSACTION_EMPLOYEE, createDTO.getTransactionEmployeeUsername()));

        Optional<PackageBox> packageBox = packageBoxRepository.findById(createDTO.getPackageBoxId());
        if (packageBox.isEmpty())
            throw new NotExistException(Message.notExist(EntityType.PACKAGE_BOX, createDTO.getPackageBoxId().toString()));

        CustomerAndTransactionDeliver customerAndTransactionDeliver = new CustomerAndTransactionDeliver(customer, transactionEmployee.getTransactionPointLeader(), createDTO.getState(), new Timestamp(System.currentTimeMillis()), null, packageBox.get());
        customerAndTransactionDeliverRepository.save(customerAndTransactionDeliver);
    }

    public void confirm(DeliverConfirmByTransactionEmployeeDTO confirmDTO) throws NotExistException {
        Optional<CustomerAndTransactionDeliver> optionalCustomerAndTransactionDeliver = customerAndTransactionDeliverRepository.findById(confirmDTO.getId());
        if (optionalCustomerAndTransactionDeliver.isEmpty()) {
            throw new NotExistException(Message.notExist(EntityType.CUSTOMER_AND_TRANSACTION, confirmDTO.getId().toString()));
        }

        CustomerAndTransactionDeliver customerAndTransactionDeliver = optionalCustomerAndTransactionDeliver.get();
        customerAndTransactionDeliver.setState(confirmDTO.getDeliverState());
        customerAndTransactionDeliver.setReceiveTime(new Timestamp(System.currentTimeMillis()));

        customerAndTransactionDeliverRepository.save(customerAndTransactionDeliver);
    }
}
