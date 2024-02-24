package com.backend.controller;

import com.backend.dto.SignInDTO;
import com.backend.dto.viewInfo.GatherPointLeaderViewInfo;
import com.backend.exception.NotExistException;
import com.backend.model.CompanyLeader;
import com.backend.service.CompanyLeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/")
public class CompanyLeaderController {
    @Autowired
    CompanyLeaderService companyLeaderService;

    @PostMapping("information")
    public ResponseEntity<?> getCompanyLeaderInformation(@RequestBody String companyLeaderUsername) {
        try {
            CompanyLeader companyLeader = companyLeaderService.getCompanyLeaderInformation(companyLeaderUsername);
            return new ResponseEntity<>(companyLeader, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("sign-in")
    public ResponseEntity<String> signIn(@RequestBody SignInDTO signInDTO) {
        return new ResponseEntity<>(companyLeaderService.signIn(signInDTO), HttpStatus.OK);
    }
}
