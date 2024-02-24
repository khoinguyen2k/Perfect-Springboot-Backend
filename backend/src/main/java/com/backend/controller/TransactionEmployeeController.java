package com.backend.controller;

import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.dto.SignInDTO;
import com.backend.dto.delete.TransactionEmployeeDeleteDTO;
import com.backend.dto.signUp.TransactionEmployeeSignUpDTO;
import com.backend.dto.update.TransactionEmployeeUpdateDTO;
import com.backend.dto.viewInfo.GatherEmployeeViewInfo;
import com.backend.dto.viewInfo.TransactionEmployeeViewInfo;
import com.backend.exception.NotExistException;
import com.backend.model.TransactionEmployee;
import com.backend.service.TransactionEmployeeService;
import com.backend.service.TransactionPointLeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee/transaction/")
public class TransactionEmployeeController {
    @Autowired
    TransactionEmployeeService transactionEmployeeService;

    @Autowired
    TransactionPointLeaderService transactionPointLeaderService;

    @PostMapping("information")
    public ResponseEntity<?> getTransactionEmployeeInformation(@RequestBody String transactionEmployeeUsername) {
        try {
            TransactionEmployeeViewInfo transactionEmployeeViewInfo = transactionEmployeeService.getTransactionEmployeeInformation(transactionEmployeeUsername);
            return new ResponseEntity<>(transactionEmployeeUsername, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("list")
    public ResponseEntity<?> getTransactionEmployeeList(@RequestBody String transactionPointLeaderUsername) {
        if (transactionPointLeaderService.isUsernameNotExist(transactionPointLeaderUsername)) {
            return new ResponseEntity<>(Message.notExist(EntityType.TRANSACTION_POINT_LEADER, transactionPointLeaderUsername), HttpStatus.OK);
        }

        List<TransactionEmployee> transactionEmployeeList = transactionEmployeeService.getTransactionEmployeeList(transactionPointLeaderUsername);
        return new ResponseEntity<>(transactionEmployeeList, HttpStatus.OK);
    }

    @PostMapping("sign-up")
    public ResponseEntity<String> signUp(@RequestBody TransactionEmployeeSignUpDTO transactionEmployeeSignUpDTO) {
        try {
            transactionEmployeeService.signUp(transactionEmployeeSignUpDTO);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Transaction employee sign up success!", HttpStatus.OK);
    }

    @PostMapping("sign-in")
    public ResponseEntity<String> signIn(@RequestBody SignInDTO signInDTO) {
        return new ResponseEntity<>(transactionEmployeeService.signIn(signInDTO), HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<String> updateTransactionEmployee(@RequestBody TransactionEmployeeUpdateDTO transactionEmployeeUpdateDTO) {
        if (transactionPointLeaderService.isUsernameNotExist(transactionEmployeeUpdateDTO.getTransactionPointLeaderUsername())) {
            return new ResponseEntity<>(Message.notExist(EntityType.TRANSACTION_POINT_LEADER, transactionEmployeeUpdateDTO.getTransactionPointLeaderUsername()), HttpStatus.OK);
        }

        try {
            transactionEmployeeService.update(transactionEmployeeUpdateDTO);
            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteTransactionEmployee(@RequestBody TransactionEmployeeDeleteDTO transactionEmployeeDeleteDTO) {
        if (transactionPointLeaderService.isUsernameNotExist(transactionEmployeeDeleteDTO.getTransactionPointLeaderUsername())) {
            return new ResponseEntity<>(Message.notExist(EntityType.TRANSACTION_POINT_LEADER, transactionEmployeeDeleteDTO.getTransactionPointLeaderUsername()), HttpStatus.OK);
        }

        try {
            transactionEmployeeService.delete(transactionEmployeeDeleteDTO.getTransactionEmployeeUsername());
            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }
}
