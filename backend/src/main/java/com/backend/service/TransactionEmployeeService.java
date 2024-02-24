package com.backend.service;

import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.constant.SignInResponse;
import com.backend.dto.SignInDTO;
import com.backend.dto.signUp.TransactionEmployeeSignUpDTO;
import com.backend.dto.update.GatherEmployeeUpdateDTO;
import com.backend.dto.update.TransactionEmployeeUpdateDTO;
import com.backend.dto.viewInfo.TransactionEmployeeViewInfo;
import com.backend.exception.NotExistException;
import com.backend.model.GatherEmployee;
import com.backend.model.GatherPointLeader;
import com.backend.model.TransactionEmployee;
import com.backend.model.TransactionPointLeader;
import com.backend.repository.TransactionEmployeeRepository;
import com.backend.repository.TransactionPointLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TransactionEmployeeService {
    @Autowired
    TransactionEmployeeRepository transactionEmployeeRepository;

    @Autowired
    TransactionPointLeaderRepository transactionPointLeaderRepository;

    public List<TransactionEmployee> getTransactionEmployeeList(String transactionPointLeaderUsername) {
        TransactionPointLeader transactionPointLeader = transactionPointLeaderRepository.findByUsername(transactionPointLeaderUsername);

        return transactionEmployeeRepository.findAllByTransactionPointLeader(transactionPointLeader);
    }

    public TransactionEmployeeViewInfo getTransactionEmployeeInformation(String username) throws NotExistException {
        TransactionEmployee transactionEmployee = transactionEmployeeRepository.findByUsername(username);
        if (Objects.isNull(transactionEmployee))
            throw new NotExistException(Message.notExist(EntityType.TRANSACTION_EMPLOYEE, username));
        else
            return new TransactionEmployeeViewInfo(transactionEmployee);
    }

    public void signUp(TransactionEmployeeSignUpDTO transactionEmployeeSignUpDTO) throws NotExistException {
        TransactionPointLeader transactionPointLeader = transactionPointLeaderRepository.findByUsername(transactionEmployeeSignUpDTO.getTransactionPointLeaderUsername());
        if (Objects.isNull(transactionPointLeader)) {
            throw new NotExistException("There's no transaction point leader with username " + transactionEmployeeSignUpDTO.getTransactionPointLeaderUsername() + "!");
        }

        TransactionEmployee transactionEmployee = new TransactionEmployee(transactionEmployeeSignUpDTO, transactionPointLeader);
        transactionEmployeeRepository.save(transactionEmployee);
    }

    public String signIn(SignInDTO signInDTO) {
        TransactionEmployee transactionEmployee = transactionEmployeeRepository.findByUsername(signInDTO.getUsername());
        if (Objects.isNull(transactionEmployee)) {
            return SignInResponse.INVALID_USERNAME;
        }

        if (!signInDTO.getPassword().equals(transactionEmployee.getPassword())) {
            return SignInResponse.WRONG_PASSWORD;
        } else {
            return SignInResponse.SIGN_IN_SUCCESSFUL;
        }
    }

    public void update(TransactionEmployeeUpdateDTO transactionEmployeeUpdateDTO) throws NotExistException {
        TransactionEmployee transactionEmployee = transactionEmployeeRepository.findByUsername(transactionEmployeeUpdateDTO.getTransactionEmployeeUsername());
        if (Objects.isNull(transactionEmployee)) {
            throw new NotExistException(Message.notExist(EntityType.TRANSACTION_EMPLOYEE, transactionEmployeeUpdateDTO.getTransactionEmployeeUsername()));
        }

        transactionEmployee.updateInfo(transactionEmployeeUpdateDTO);
    }

    public void delete(String transactionEmployeeUsername) throws NotExistException {
        TransactionEmployee transactionEmployee = transactionEmployeeRepository.findByUsername(transactionEmployeeUsername);
        if (Objects.isNull(transactionEmployee)) {
            throw new NotExistException(Message.notExist(EntityType.TRANSACTION_EMPLOYEE, transactionEmployeeUsername));
        }

        transactionEmployeeRepository.delete(transactionEmployee);
    }
}
