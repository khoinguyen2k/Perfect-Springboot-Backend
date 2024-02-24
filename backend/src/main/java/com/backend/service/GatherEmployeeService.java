package com.backend.service;

import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.constant.SignInResponse;
import com.backend.dto.signUp.GatherEmployeeSignUpDTO;
import com.backend.dto.SignInDTO;
import com.backend.dto.update.GatherEmployeeUpdateDTO;
import com.backend.dto.viewInfo.GatherEmployeeViewInfo;
import com.backend.exception.NotExistException;
import com.backend.model.GatherEmployee;
import com.backend.model.GatherPointLeader;
import com.backend.repository.GatherEmployeeRepository;
import com.backend.repository.GatherPointLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GatherEmployeeService {
    @Autowired
    GatherEmployeeRepository gatherEmployeeRepository;

    @Autowired
    GatherPointLeaderRepository gatherPointLeaderRepository;

    public List<GatherEmployee> getGatherEmployeeList(String gatherPointLeaderUsername) {
        GatherPointLeader gatherPointLeader = gatherPointLeaderRepository.findByUsername(gatherPointLeaderUsername);

        return gatherEmployeeRepository.findAllByGatherPointLeader(gatherPointLeader);
    }

    public GatherEmployeeViewInfo getGatherEmployeeInformation(String username) throws NotExistException {
        GatherEmployee gatherEmployee = gatherEmployeeRepository.findByUsername(username);
        if (Objects.isNull(gatherEmployee))
            throw new NotExistException(Message.notExist(EntityType.GATHER_EMPLOYEE, username));
        else
            return new GatherEmployeeViewInfo(gatherEmployee);
    }


    public void signUp(GatherEmployeeSignUpDTO gatherEmployeeSignUpDTO) throws NotExistException {
        GatherPointLeader gatherPointLeader = gatherPointLeaderRepository.findByUsername(gatherEmployeeSignUpDTO.getGatherPointLeaderUsername());
        if (Objects.isNull(gatherPointLeader)) {
            throw new NotExistException("There's no gather point leader with username " + gatherEmployeeSignUpDTO.getGatherPointLeaderUsername() + "!");
        }

        GatherEmployee gatherEmployee = new GatherEmployee(gatherEmployeeSignUpDTO, gatherPointLeader);
        gatherEmployeeRepository.save(gatherEmployee);
    }

    public String signIn(SignInDTO signInDTO) {
        GatherEmployee gatherEmployee = gatherEmployeeRepository.findByUsername(signInDTO.getUsername());
        if (Objects.isNull(gatherEmployee)) {
            return SignInResponse.INVALID_USERNAME;
        }

        if (!gatherEmployee.getPassword().equals(signInDTO.getPassword())) {
            return SignInResponse.WRONG_PASSWORD;
        } else {
            return SignInResponse.SIGN_IN_SUCCESSFUL;
        }
    }

    public void update(GatherEmployeeUpdateDTO gatherEmployeeUpdateDTO) throws NotExistException {
        GatherEmployee gatherEmployee = gatherEmployeeRepository.findByUsername(gatherEmployeeUpdateDTO.getGatherEmployeeUsername());
        if (Objects.isNull(gatherEmployee)) {
            throw new NotExistException(Message.notExist(EntityType.GATHER_EMPLOYEE, gatherEmployeeUpdateDTO.getGatherEmployeeUsername()));
        }

        gatherEmployee.updateInfo(gatherEmployeeUpdateDTO);
    }

    public void delete(String gatherEmployeeUsername) throws NotExistException {
        GatherEmployee gatherEmployee = gatherEmployeeRepository.findByUsername(gatherEmployeeUsername);
        if (Objects.isNull(gatherEmployee)) {
            throw new NotExistException(Message.notExist(EntityType.GATHER_EMPLOYEE, gatherEmployeeUsername));
        }

        gatherEmployeeRepository.delete(gatherEmployee);
    }
}
