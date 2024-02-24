package com.backend.controller;

import com.backend.constant.Message;
import com.backend.dto.confirm.DeliverConfirmByGatherEmployeeDTO;
import com.backend.dto.confirm.DeliverConfirmByTransactionEmployeeDTO;
import com.backend.dto.create.CustomerAndTransactionDeliverCreateDTO;
import com.backend.exception.NotExistException;
import com.backend.model.CustomerAndTransactionDeliver;
import com.backend.model.GatherAndGatherDeliver;
import com.backend.service.CustomerAndTransactionDeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("deliver/customer-to-transaction/")
public class CustomerAndTransactionDeliverController {
    @Autowired
    CustomerAndTransactionDeliverService customerAndTransactionDeliverService;

    @PostMapping("create")
    public ResponseEntity<String> createCustomerAndTransactionDeliver(@RequestBody CustomerAndTransactionDeliverCreateDTO createDTO) {
        try {
            customerAndTransactionDeliverService.createCustomerAndTransactionDeliver(createDTO);
            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("confirm-by/transaction/gather")
    public ResponseEntity<String> transactionEmployeeConfirmDeliver(@RequestBody DeliverConfirmByTransactionEmployeeDTO confirmDTO) {
        try {
            customerAndTransactionDeliverService.confirm(confirmDTO);

            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }
}
