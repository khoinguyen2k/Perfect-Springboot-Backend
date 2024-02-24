package com.backend.controller;

import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.dto.GatherItemDTO;
import com.backend.dto.TransactionItemDTO;
import com.backend.exception.NotExistException;
import com.backend.model.CompanyLeader;
import com.backend.model.PackageBox;
import com.backend.service.CompanyLeaderService;
import com.backend.service.DeliveringItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("delivering-item/")
public class DeliveringItemController {
    @Autowired
    DeliveringItemService deliveringItemService;

    @Autowired
    CompanyLeaderService companyLeaderService;

    @PostMapping("list/send/transaction/{transactionPointLeaderUsername}")
    public ResponseEntity<?> getTransactionSendItemList(@PathVariable("transactionPointLeaderUsername") String transactionPointLeaderUsername) {
        try {
            List<PackageBox> transactionSendPackageBoxList = deliveringItemService.getTransactionSendItemList(transactionPointLeaderUsername);

            return new ResponseEntity<>(transactionSendPackageBoxList, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("list/receive/transaction/{transactionPointLeaderUsername}")
    public ResponseEntity<?> getTransactionReceiveItemList(@PathVariable("transactionPointLeaderUsername") String transactionPointLeaderUsername) {
        try {
            List<PackageBox> transactionReceivePackageBoxList = deliveringItemService.getTransactionReceiveItemList(transactionPointLeaderUsername);

            return new ResponseEntity<>(transactionReceivePackageBoxList, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("list/send/gather/{gatherPointLeaderUsername}")
    public ResponseEntity<?> getGatherSendItemList(@PathVariable("gatherPointLeaderUsername") String gatherPointLeaderUsername) {
        try {
            List<PackageBox> gatherSendPackageBoxList = deliveringItemService.getGatherSendItemList(gatherPointLeaderUsername);

            return new ResponseEntity<>(gatherSendPackageBoxList, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("list/receive/gather/{gatherPointLeaderUsername}")
    public ResponseEntity<?> getGatherReceiveItemList(@PathVariable("gatherPointLeaderUsername") String gatherPointLeaderUsername) {
        try {
            List<PackageBox> gatherReceivePackageBoxList = deliveringItemService.getGatherReceiveItemList(gatherPointLeaderUsername);

            return new ResponseEntity<>(gatherReceivePackageBoxList, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("list/send/gather/all")
    public ResponseEntity<?> getAllGatherSendItemList(@RequestBody String companyLeaderUsername) throws NotExistException {
        if (companyLeaderService.isUsernameNotExist(companyLeaderUsername))
            return new ResponseEntity<>(Message.notExist(EntityType.COMPANY_LEADER, companyLeaderUsername), HttpStatus.OK);

        List<GatherItemDTO> gatherItemDTOList = deliveringItemService.getAllGatherSendItemList();
        return new ResponseEntity<>(gatherItemDTOList, HttpStatus.OK);
    }

    @PostMapping("list/receive/gather/all")
    public ResponseEntity<?> getAllGatherReceiveItemList(@RequestBody String companyLeaderUsername) throws NotExistException {
        if (companyLeaderService.isUsernameNotExist(companyLeaderUsername))
            return new ResponseEntity<>(Message.notExist(EntityType.COMPANY_LEADER, companyLeaderUsername), HttpStatus.OK);

        List<GatherItemDTO> gatherItemDTOList = deliveringItemService.getAllGatherReceiveItemList();
        return new ResponseEntity<>(gatherItemDTOList, HttpStatus.OK);
    }

    @PostMapping("list/send/transaction/all")
    public ResponseEntity<?> getAllTransactionSendItemList(@RequestBody String companyLeaderUsername) throws NotExistException {
        if (companyLeaderService.isUsernameNotExist(companyLeaderUsername))
            return new ResponseEntity<>(Message.notExist(EntityType.COMPANY_LEADER, companyLeaderUsername), HttpStatus.OK);

        List<TransactionItemDTO> transactionItemDTOList = deliveringItemService.getAllTransactionSendItemList();
        return new ResponseEntity<>(transactionItemDTOList, HttpStatus.OK);
    }

    @PostMapping("list/receive/transaction/all")
    public ResponseEntity<?> getAllTransactionReceiveItemList(@RequestBody String companyLeaderUsername) throws NotExistException {
        if (companyLeaderService.isUsernameNotExist(companyLeaderUsername))
            return new ResponseEntity<>(Message.notExist(EntityType.COMPANY_LEADER, companyLeaderUsername), HttpStatus.OK);

        List<TransactionItemDTO> transactionItemDTOList = deliveringItemService.getAllTransactionReceiveItemList();
        return new ResponseEntity<>(transactionItemDTOList, HttpStatus.OK);
    }

}
