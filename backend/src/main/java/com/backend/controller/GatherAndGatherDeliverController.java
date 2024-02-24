package com.backend.controller;

import com.backend.constant.Message;
import com.backend.dto.confirm.DeliverConfirmByGatherEmployeeDTO;
import com.backend.dto.create.GatherAndGatherDeliverCreateDTO;
import com.backend.dto.viewInfo.GatherEmployeeViewInfo;
import com.backend.exception.NotExistException;
import com.backend.model.GatherAndGatherDeliver;
import com.backend.model.GatherPointLeader;
import com.backend.service.GatherAndGatherDeliverService;
import com.backend.service.GatherEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("deliver/gather-to-gather/")
public class GatherAndGatherDeliverController {
    @Autowired
    GatherAndGatherDeliverService gatherAndGatherDeliverService;

    @Autowired
    GatherEmployeeService gatherEmployeeService;

    @PostMapping("list")
    public ResponseEntity<?> getAllReceiveGatherAndGatherDeliverList(@RequestBody String gatherEmployeeUsername) {
        try {
            GatherEmployeeViewInfo gatherEmployeeViewInfo = gatherEmployeeService.getGatherEmployeeInformation(gatherEmployeeUsername);

            GatherPointLeader gatherPointLeader = gatherEmployeeViewInfo.getGatherPointLeader();
            List<GatherAndGatherDeliver> gatherAndGatherDeliverList = gatherAndGatherDeliverService.getAllReceiveGatherAndGatherDeliverList(gatherPointLeader);

            return new ResponseEntity<>(gatherAndGatherDeliverList, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("create")
    public ResponseEntity<String> createGatherAndGatherDeliver(@RequestBody GatherAndGatherDeliverCreateDTO createDTO) {
        try {
            gatherAndGatherDeliverService.createGatherAndGatherDeliver(createDTO);
            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("confirm-by/employee/gather")
    public ResponseEntity<String> gatherEmployeeConfirmDeliver(@RequestBody DeliverConfirmByGatherEmployeeDTO confirmDTO) {
        try {
            gatherAndGatherDeliverService.confirm(confirmDTO);

            return new ResponseEntity<>(Message.SUCCESSFUL, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }
}
