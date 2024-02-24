package com.backend.controller;

import com.backend.constant.Message;
import com.backend.dto.confirm.DeliverConfirmByGatherEmployeeDTO;
import com.backend.dto.confirm.DeliverConfirmByTransactionEmployeeDTO;
import com.backend.dto.create.GatherAndGatherDeliverCreateDTO;
import com.backend.dto.create.GatherAndTransactionDeliverCreateDTO;
import com.backend.dto.create.TransactionAndGatherDeliverCreateDTO;
import com.backend.dto.viewInfo.GatherEmployeeViewInfo;
import com.backend.exception.NotExistException;
import com.backend.model.GatherAndGatherDeliver;
import com.backend.model.GatherPointLeader;
import com.backend.model.TransactionAndGatherDeliver;
import com.backend.service.GatherEmployeeService;
import com.backend.service.TransactionAndGatherDeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("deliver/transaction-to-gather/")
public class TransactionAndGatherDeliverController {
    @Autowired
    TransactionAndGatherDeliverService transactionAndGatherDeliverService;

    @Autowired
    GatherEmployeeService gatherEmployeeService;

    @PostMapping("list")
    public ResponseEntity<?> getAllReceiveGatherAndGatherDeliverList(@RequestBody String gatherEmployeeUsername) {
        try {
            GatherEmployeeViewInfo gatherEmployeeViewInfo = gatherEmployeeService.getGatherEmployeeInformation(gatherEmployeeUsername);

            GatherPointLeader gatherPointLeader = gatherEmployeeViewInfo.getGatherPointLeader();
            List<TransactionAndGatherDeliver> transactionAndGatherDeliverList = transactionAndGatherDeliverService.getAllReceiveTransactionAndGatherDeliverList(gatherPointLeader);

            return new ResponseEntity<>(transactionAndGatherDeliverList, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("create/by/employee/transaction")
    public ResponseEntity<String> createTransactionAndGatherDeliver(@RequestBody TransactionAndGatherDeliverCreateDTO createDTO) {
        try {
            transactionAndGatherDeliverService.createTransactionAndGatherDeliver(createDTO);
            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("create/by/employee/gather")
    public ResponseEntity<String> createTransactionAndGatherDeliver(@RequestBody GatherAndTransactionDeliverCreateDTO createDTO) {
        try {
            transactionAndGatherDeliverService.createGatherAndTransactionDeliver(createDTO);
            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("confirm-by/employee/gather")
    public ResponseEntity<String> gatherEmployeeConfirmDeliver(@RequestBody DeliverConfirmByGatherEmployeeDTO confirmDTO) {
        try {
            transactionAndGatherDeliverService.confirm(confirmDTO);

            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("confirm-by/employee/transaction")
    public ResponseEntity<String> transactionEmployeeConfirmDeliver(@RequestBody DeliverConfirmByTransactionEmployeeDTO confirmDTO) {
        try {
            transactionAndGatherDeliverService.confirm(confirmDTO);

            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }
}
