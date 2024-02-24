package com.backend.service;

import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.constant.SignInResponse;
import com.backend.dto.signUp.GatherPointLeaderSignUpDTO;
import com.backend.dto.SignInDTO;
import com.backend.dto.update.GatherPointLeaderUpdateDTO;
import com.backend.dto.viewInfo.GatherPointLeaderViewInfo;
import com.backend.exception.NotExistException;
import com.backend.model.CompanyLeader;
import com.backend.model.GatherPointLeader;
import com.backend.repository.CompanyLeaderRepository;
import com.backend.repository.GatherPointLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GatherPointLeaderService {
    @Autowired
    GatherPointLeaderRepository gatherPointLeaderRepository;

    @Autowired
    CompanyLeaderRepository companyLeaderRepository;

    public List<GatherPointLeader> getGatherPointLeaderList() {
        return gatherPointLeaderRepository.findAll();
    }

    public boolean isUsernameNotExist(String username) {
        GatherPointLeader gatherPointLeader = gatherPointLeaderRepository.findByUsername(username);
        return Objects.isNull(gatherPointLeader);
    }

    public GatherPointLeaderViewInfo getGatherPointLeaderInformation(String username) throws NotExistException {
        GatherPointLeader gatherPointLeader = gatherPointLeaderRepository.findByUsername(username);
        if (Objects.isNull(gatherPointLeader))
            throw new NotExistException(Message.notExist(EntityType.GATHER_POINT_LEADER, username));
        else
            return new GatherPointLeaderViewInfo(gatherPointLeader);
    }

    public void signUp(GatherPointLeaderSignUpDTO gatherPointLeaderSignUpDTO) throws NotExistException {
        CompanyLeader companyLeader = companyLeaderRepository.findByUsername(gatherPointLeaderSignUpDTO.getCompanyLeaderUsername());
        if (Objects.isNull(companyLeader)) {
            throw new NotExistException("There's no company leader with username " + gatherPointLeaderSignUpDTO.getCompanyLeaderUsername() + "!");
        }

        GatherPointLeader gatherPointLeader = new GatherPointLeader(gatherPointLeaderSignUpDTO);
        gatherPointLeaderRepository.save(gatherPointLeader);
    }

    public String signIn(SignInDTO signInDTO) {
        GatherPointLeader gatherPointLeader = gatherPointLeaderRepository.findByUsername(signInDTO.getUsername());
        if (Objects.isNull(gatherPointLeader)) {
            return SignInResponse.INVALID_USERNAME;
        }

        if (!gatherPointLeader.getPassword().equals(signInDTO.getPassword())) {
            return SignInResponse.WRONG_PASSWORD;
        } else {
            return SignInResponse.SIGN_IN_SUCCESSFUL;
        }
    }

    public void update(GatherPointLeaderUpdateDTO gatherPointLeaderUpdateDTO) throws NotExistException {
        GatherPointLeader gatherPointLeader = gatherPointLeaderRepository.findByUsername(gatherPointLeaderUpdateDTO.getGatherPointLeaderUsername());
        if (Objects.isNull(gatherPointLeader)) {
            throw new NotExistException(Message.notExist(EntityType.GATHER_POINT_LEADER, gatherPointLeaderUpdateDTO.getGatherPointLeaderUsername()));
        }

        gatherPointLeader.updateInfo(gatherPointLeaderUpdateDTO);
    }

    public void delete(String gatherPointLeaderUsername) throws NotExistException {
        GatherPointLeader gatherPointLeader = gatherPointLeaderRepository.findByUsername(gatherPointLeaderUsername);
        if (Objects.isNull(gatherPointLeader)) {
            throw new NotExistException(Message.notExist(EntityType.GATHER_POINT_LEADER, gatherPointLeaderUsername));
        }

        gatherPointLeaderRepository.delete(gatherPointLeader);
    }
}
