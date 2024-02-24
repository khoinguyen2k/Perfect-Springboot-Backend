package com.backend.service;

import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.constant.SignInResponse;
import com.backend.dto.SignInDTO;
import com.backend.dto.signUp.TransactionPointLeaderSignUpDTO;
import com.backend.dto.update.TransactionPointLeaderUpdateDTO;
import com.backend.dto.viewInfo.TransactionPointLeaderViewInfo;
import com.backend.exception.NotExistException;
import com.backend.model.GatherPointLeader;
import com.backend.model.TransactionPointLeader;
import com.backend.repository.GatherPointLeaderRepository;
import com.backend.repository.TransactionPointLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TransactionPointLeaderService {
    @Autowired
    TransactionPointLeaderRepository transactionPointLeaderRepository;

    @Autowired
    GatherPointLeaderRepository gatherPointLeaderRepository;

    public List<TransactionPointLeader> getAllTransactionPointLeaderList() {
        return transactionPointLeaderRepository.findAll();
    }

    public boolean isUsernameNotExist(String username) {
        TransactionPointLeader transactionPointLeader = transactionPointLeaderRepository.findByUsername(username);
        return Objects.isNull(transactionPointLeader);
    }

    public TransactionPointLeaderViewInfo getTransactionPointLeaderInformation(String username) throws NotExistException {
        TransactionPointLeader transactionPointLeader = transactionPointLeaderRepository.findByUsername(username);
        if (Objects.isNull(transactionPointLeader))
            throw new NotExistException(Message.notExist(EntityType.TRANSACTION_POINT_LEADER, username));
        else
            return new TransactionPointLeaderViewInfo(transactionPointLeader);
    }

    public TransactionPointLeader getTransactionPointLeaderByUsername(String username) {
        return transactionPointLeaderRepository.findByUsername(username);
    }

    public void signUp(TransactionPointLeaderSignUpDTO transactionPointLeaderSignUpDTO) throws NotExistException {
        GatherPointLeader gatherPointLeader = gatherPointLeaderRepository.findByUsername(transactionPointLeaderSignUpDTO.getGatherPointLeaderUsername());
        if (Objects.isNull(gatherPointLeader)) {
            throw new NotExistException("There's no gather point leader with username " + transactionPointLeaderSignUpDTO.getGatherPointLeaderUsername() + "!");
        }

        TransactionPointLeader transactionPointLeader = new TransactionPointLeader(transactionPointLeaderSignUpDTO, gatherPointLeader);
        transactionPointLeaderRepository.save(transactionPointLeader);
    }

    public String signIn(SignInDTO signInDTO) {
        TransactionPointLeader transactionPointLeader = transactionPointLeaderRepository.findByUsername(signInDTO.getUsername());
        if (Objects.isNull(transactionPointLeader)) {
            return SignInResponse.INVALID_USERNAME;
        }

        if (!signInDTO.getPassword().equals(transactionPointLeader.getPassword())) {
            return SignInResponse.WRONG_PASSWORD;
        } else {
            return SignInResponse.SIGN_IN_SUCCESSFUL;
        }
    }

    public void update(TransactionPointLeaderUpdateDTO transactionPointLeaderUpdateDTO) throws NotExistException {
        TransactionPointLeader transactionPointLeader = transactionPointLeaderRepository.findByUsername(transactionPointLeaderUpdateDTO.getTransactionPointLeaderUsername());
        if (Objects.isNull(transactionPointLeader)) {
            throw new NotExistException(Message.notExist(EntityType.TRANSACTION_POINT_LEADER, transactionPointLeaderUpdateDTO.getTransactionPointLeaderUsername()));
        }

        transactionPointLeader.updateInfo(transactionPointLeaderUpdateDTO);
    }

    public void delete(String transactionPointLeaderUsername) throws NotExistException {
        TransactionPointLeader transactionPointLeader = transactionPointLeaderRepository.findByUsername(transactionPointLeaderUsername);
        if (Objects.isNull(transactionPointLeader)) {
            throw new NotExistException(Message.notExist(EntityType.TRANSACTION_POINT_LEADER, transactionPointLeaderUsername));
        }

        transactionPointLeaderRepository.delete(transactionPointLeader);
    }
}
