package com.backend.service;

import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.constant.SignInResponse;
import com.backend.dto.SignInDTO;
import com.backend.dto.viewInfo.GatherPointLeaderViewInfo;
import com.backend.exception.NotExistException;
import com.backend.model.CompanyLeader;
import com.backend.model.GatherPointLeader;
import com.backend.repository.CompanyLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CompanyLeaderService {
    @Autowired
    CompanyLeaderRepository companyLeaderRepository;

    public boolean isUsernameNotExist(String username) {
        CompanyLeader companyLeader = companyLeaderRepository.findByUsername(username);
        return Objects.isNull(companyLeader);
    }

    public String signIn(SignInDTO signInDTO) {
        CompanyLeader companyLeader = companyLeaderRepository.findByUsername(signInDTO.getUsername());
        if (Objects.isNull(companyLeader)) {
            return SignInResponse.INVALID_USERNAME;
        }

        if (!signInDTO.getPassword().equals(companyLeader.getPassword())) {
            return SignInResponse.WRONG_PASSWORD;
        } else {
            return SignInResponse.SIGN_IN_SUCCESSFUL;
        }
    }

    public CompanyLeader getCompanyLeaderInformation(String username) throws NotExistException {
        CompanyLeader companyLeader = companyLeaderRepository.findByUsername(username);
        if (Objects.isNull(companyLeader))
            throw new NotExistException(Message.notExist(EntityType.GATHER_POINT_LEADER, username));
        else
            return companyLeader;
    }
}
