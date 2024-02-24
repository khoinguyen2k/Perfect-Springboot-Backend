package com.backend.controller;

import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.dto.SignInDTO;
import com.backend.dto.delete.TransactionPointLeaderDeleteDTO;
import com.backend.dto.signUp.TransactionPointLeaderSignUpDTO;
import com.backend.dto.update.TransactionPointLeaderUpdateDTO;
import com.backend.dto.viewInfo.GatherPointLeaderViewInfo;
import com.backend.dto.viewInfo.TransactionPointLeaderViewInfo;
import com.backend.exception.NotExistException;
import com.backend.model.TransactionPointLeader;
import com.backend.service.GatherPointLeaderService;
import com.backend.service.TransactionPointLeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("point-leader/transaction/")
public class TransactionPointLeaderController {
    @Autowired
    TransactionPointLeaderService transactionPointLeaderService;

    @Autowired
    GatherPointLeaderService gatherPointLeaderService;

    @GetMapping("tools/list")
    public ResponseEntity<List<TransactionPointLeader>> getAllTransactionPointLeaderList() {
        List<TransactionPointLeader> transactionPointLeaderList = transactionPointLeaderService.getAllTransactionPointLeaderList();
        return new ResponseEntity<>(transactionPointLeaderList, HttpStatus.OK);
    }

    @PostMapping("information")
    public ResponseEntity<?> getTransactionPointLeaderInformation(@RequestBody String transactionPointLeaderUsername) {
        try {
            TransactionPointLeaderViewInfo transactionPointLeaderViewInfo = transactionPointLeaderService.getTransactionPointLeaderInformation(transactionPointLeaderUsername);
            return new ResponseEntity<>(transactionPointLeaderViewInfo, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("list")
    public ResponseEntity<?> getTransactionPointLeaderList(@RequestBody String gatherPointLeaderUsername) {
        if (gatherPointLeaderService.isUsernameNotExist(gatherPointLeaderUsername)) {
            return new ResponseEntity<>(Message.notExist(EntityType.GATHER_POINT_LEADER, gatherPointLeaderUsername), HttpStatus.OK);
        }

        List<TransactionPointLeader> transactionPointLeaderList = transactionPointLeaderService.getAllTransactionPointLeaderList();
        return new ResponseEntity<>(transactionPointLeaderList, HttpStatus.OK);
    }

    @PostMapping("sign-up")
    public ResponseEntity<String> signUp(@RequestBody TransactionPointLeaderSignUpDTO transactionPointLeaderSignUpDTO) {
        try {
            transactionPointLeaderService.signUp(transactionPointLeaderSignUpDTO);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Transaction point leader sign up success!", HttpStatus.OK);
    }

    @PostMapping("sign-in")
    public ResponseEntity<String> signIn(@RequestBody SignInDTO signInDTO) {
        return new ResponseEntity<>(transactionPointLeaderService.signIn(signInDTO), HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<String> updateTransactionPointLeader(@RequestBody TransactionPointLeaderUpdateDTO transactionPointLeaderUpdateDTO) {
        if (gatherPointLeaderService.isUsernameNotExist(transactionPointLeaderUpdateDTO.getGatherPointLeaderUsername())) {
            return new ResponseEntity<>(Message.notExist(EntityType.GATHER_POINT_LEADER, transactionPointLeaderUpdateDTO.getGatherPointLeaderUsername()), HttpStatus.OK);
        }

        try {
            transactionPointLeaderService.update(transactionPointLeaderUpdateDTO);
            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteTransactionPointLeader(@RequestBody TransactionPointLeaderDeleteDTO transactionPointLeaderDeleteDTO) {
        if (gatherPointLeaderService.isUsernameNotExist(transactionPointLeaderDeleteDTO.getGatherPointLeaderUsername())) {
            return new ResponseEntity<>(Message.notExist(EntityType.GATHER_POINT_LEADER, transactionPointLeaderDeleteDTO.getGatherPointLeaderUsername()), HttpStatus.OK);
        }

        try {
            transactionPointLeaderService.delete(transactionPointLeaderDeleteDTO.getTransactionPointLeaderUsername());
            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }
}
