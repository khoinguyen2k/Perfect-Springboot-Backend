package com.backend.controller;

import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.dto.delete.GatherEmployeeDeleteDTO;
import com.backend.dto.delete.GatherPointLeaderDeleteDTO;
import com.backend.dto.signUp.GatherEmployeeSignUpDTO;
import com.backend.dto.SignInDTO;
import com.backend.dto.update.GatherEmployeeUpdateDTO;
import com.backend.dto.update.GatherPointLeaderUpdateDTO;
import com.backend.dto.viewInfo.GatherEmployeeViewInfo;
import com.backend.dto.viewInfo.GatherPointLeaderViewInfo;
import com.backend.exception.NotExistException;
import com.backend.model.GatherEmployee;
import com.backend.service.GatherEmployeeService;
import com.backend.service.GatherPointLeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("employee/gather/")
public class GatherEmployeeController {
    @Autowired
    GatherEmployeeService gatherEmployeeService;

    @Autowired
    GatherPointLeaderService gatherPointLeaderService;

    @PostMapping("information")
    public ResponseEntity<?> getGatherEmployeeInformation(@RequestBody String gatherEmployeeUsername) {
        try {
            GatherEmployeeViewInfo gatherEmployeeViewInfo = gatherEmployeeService.getGatherEmployeeInformation(gatherEmployeeUsername);
            return new ResponseEntity<>(gatherEmployeeViewInfo, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("list")
    public ResponseEntity<?> getGatherEmployeeList(@RequestBody String gatherPointLeaderUsername) {
        if (gatherPointLeaderService.isUsernameNotExist(gatherPointLeaderUsername)) {
            return new ResponseEntity<>(Message.notExist(EntityType.GATHER_POINT_LEADER, gatherPointLeaderUsername), HttpStatus.OK);
        }

        List<GatherEmployee> gatherEmployeeList = gatherEmployeeService.getGatherEmployeeList(gatherPointLeaderUsername);
        return new ResponseEntity<>(gatherEmployeeList, HttpStatus.OK);
    }

    @PostMapping("sign-up")
    public ResponseEntity<String> signUp(@RequestBody GatherEmployeeSignUpDTO gatherEmployeeSignUpDTO) {
        try {
            gatherEmployeeService.signUp(gatherEmployeeSignUpDTO);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }

        return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);
    }

    @PostMapping("sign-in")
    public ResponseEntity<String> signIn(@RequestBody SignInDTO signInDTO) {
        return new ResponseEntity<>(gatherEmployeeService.signIn(signInDTO), HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<String> updateGatherEmployee(@RequestBody GatherEmployeeUpdateDTO gatherEmployeeUpdateDTO) {
        if (gatherPointLeaderService.isUsernameNotExist(gatherEmployeeUpdateDTO.getGatherPointLeaderUsername())) {
            return new ResponseEntity<>(Message.notExist(EntityType.GATHER_POINT_LEADER, gatherEmployeeUpdateDTO.getGatherPointLeaderUsername()), HttpStatus.OK);
        }

        try {
            gatherEmployeeService.update(gatherEmployeeUpdateDTO);
            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteGatherEmployee(@RequestBody GatherEmployeeDeleteDTO gatherEmployeeDeleteDTO) {
        if (gatherPointLeaderService.isUsernameNotExist(gatherEmployeeDeleteDTO.getGatherPointLeaderUsername())) {
            return new ResponseEntity<>(Message.notExist(EntityType.GATHER_POINT_LEADER, gatherEmployeeDeleteDTO.getGatherPointLeaderUsername()), HttpStatus.OK);
        }

        try {
            gatherEmployeeService.delete(gatherEmployeeDeleteDTO.getGatherEmployeeUsername());
            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }
}
