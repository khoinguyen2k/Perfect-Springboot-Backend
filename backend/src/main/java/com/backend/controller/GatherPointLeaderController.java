package com.backend.controller;

import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.dto.SignInDTO;
import com.backend.dto.delete.GatherPointLeaderDeleteDTO;
import com.backend.dto.signUp.GatherPointLeaderSignUpDTO;
import com.backend.dto.update.GatherPointLeaderUpdateDTO;
import com.backend.dto.viewInfo.GatherPointLeaderViewInfo;
import com.backend.exception.NotExistException;
import com.backend.model.GatherPointLeader;
import com.backend.model.TransactionPointLeader;
import com.backend.service.CompanyLeaderService;
import com.backend.service.GatherPointLeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("point-leader/gather/")
public class GatherPointLeaderController {
    @Autowired
    GatherPointLeaderService gatherPointLeaderService;

    @Autowired
    CompanyLeaderService companyLeaderService;

    @GetMapping("tools/list")
    public ResponseEntity<List<GatherPointLeader>> getAllGatherPointLeaderList() {
        List<GatherPointLeader> gatherPointLeaderList = gatherPointLeaderService.getGatherPointLeaderList();
        return new ResponseEntity<>(gatherPointLeaderList, HttpStatus.OK);
    }

    @PostMapping("information")
    public ResponseEntity<?> getGatherPointLeaderInformation(@RequestBody String gatherPointLeaderUsername) {
        try {
            GatherPointLeaderViewInfo gatherPointLeaderViewInfo = gatherPointLeaderService.getGatherPointLeaderInformation(gatherPointLeaderUsername);
            return new ResponseEntity<>(gatherPointLeaderViewInfo, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("list")
    public ResponseEntity<?> getGatherPointLeaderList(@RequestBody String companyLeaderUsername) {
        if (companyLeaderService.isUsernameNotExist(companyLeaderUsername)) {
            return new ResponseEntity<>(Message.notExist(EntityType.COMPANY_LEADER, companyLeaderUsername), HttpStatus.OK);
        }

        List<GatherPointLeader> gatherPointLeaderList = gatherPointLeaderService.getGatherPointLeaderList();
        return new ResponseEntity<>(gatherPointLeaderList, HttpStatus.OK);
    }

    @PostMapping("sign-up")
    public ResponseEntity<String> signUp(@RequestBody GatherPointLeaderSignUpDTO gatherPointLeaderSignUpDTO) {
        try {
            gatherPointLeaderService.signUp(gatherPointLeaderSignUpDTO);
            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("sign-in")
    public ResponseEntity<String> signIn(@RequestBody SignInDTO signInDTO) {
        return new ResponseEntity<>(gatherPointLeaderService.signIn(signInDTO), HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<String> updateGatherPointLeader(@RequestBody GatherPointLeaderUpdateDTO gatherPointLeaderUpdateDTO) {
        if (companyLeaderService.isUsernameNotExist(gatherPointLeaderUpdateDTO.getCompanyLeaderUsername())) {
            return new ResponseEntity<>(Message.notExist(EntityType.COMPANY_LEADER, gatherPointLeaderUpdateDTO.getCompanyLeaderUsername()), HttpStatus.OK);
        }

        try {
            gatherPointLeaderService.update(gatherPointLeaderUpdateDTO);
            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteGatherPointLeader(@RequestBody GatherPointLeaderDeleteDTO gatherPointLeaderDeleteDTO) {
        if (companyLeaderService.isUsernameNotExist(gatherPointLeaderDeleteDTO.getCompanyLeaderUsername())) {
            return new ResponseEntity<>(Message.notExist(EntityType.COMPANY_LEADER, gatherPointLeaderDeleteDTO.getCompanyLeaderUsername()), HttpStatus.OK);
        }

        try {
            gatherPointLeaderService.delete(gatherPointLeaderDeleteDTO.getGatherPointLeaderUsername());
            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }
}
