package com.backend.controller;

import com.backend.dto.GatherItemDTO;
import com.backend.dto.TransactionItemDTO;
import com.backend.exception.NotExistException;
import com.backend.model.Customer;
import com.backend.model.PackageBox;
import com.backend.service.PackageBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item/")
public class PackageBoxController {
    @Autowired
    PackageBoxService packageBoxService;

    @GetMapping("tools/list")
    public ResponseEntity<List<PackageBox>> getAllPackageBoxList() {
        return new ResponseEntity<>(packageBoxService.getAllPackageBoxList(), HttpStatus.OK);
    }

    @PostMapping("by-point-leader/transaction/add")
    public ResponseEntity<String> addItemByTransactionPointLeader(@RequestBody TransactionItemDTO transactionItemDTO) {
        try {
            packageBoxService.addItemByTransactionPointLeader(transactionItemDTO.getPackageBox(), transactionItemDTO.getTransactionPointLeaderUsername());

            return new ResponseEntity<>("Item added!", HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("by-point-leader/gather/add")
    public ResponseEntity<String> addItemByGatherPointLeaderUsername(@RequestBody GatherItemDTO gatherItemDTO) {
        try {
            packageBoxService.addItemByGatherPointLeader(gatherItemDTO.getPackageBox(), gatherItemDTO.getGatherPointLeaderUsername());

            return new ResponseEntity<>("Item added!", HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }
}
