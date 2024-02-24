package com.backend.controller;

import com.backend.dto.viewInfo.GatherEmployeeViewInfo;
import com.backend.dto.viewInfo.TransactionEmployeeViewInfo;
import com.backend.exception.NotExistException;
import com.backend.model.PackageBox;
import com.backend.service.GatherEmployeeService;
import com.backend.service.StockItemService;
import com.backend.service.TransactionEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("stock-item/")
public class StockItemController {
    @Autowired
    StockItemService stockItemService;

    @Autowired
    GatherEmployeeService gatherEmployeeService;

    @Autowired
    TransactionEmployeeService transactionEmployeeService;

    @PostMapping("list/by/point-leader/transaction/{transactionPointLeaderUsername}")
    public ResponseEntity<?> getTransactionStockItemListByPointLeader(@PathVariable("transactionPointLeaderUsername") String transactionPointLeaderUsername) {
        try {
            List<PackageBox> transactionStockPackageBoxList = stockItemService.getTransactionStockItemList(transactionPointLeaderUsername);

            return new ResponseEntity<>(transactionStockPackageBoxList, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("list/by/point-leader/gather/{gatherPointLeaderUsername}")
    public ResponseEntity<?> getAllGatherStockItemListByPointLeader(@PathVariable("gatherPointLeaderUsername") String gatherPointLeaderUsername) {
        try {
            List<PackageBox> gatherStockPackageBoxList = stockItemService.getGatherStockItemList(gatherPointLeaderUsername);

            return new ResponseEntity<>(gatherStockPackageBoxList, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("list/by/employee/transaction/{transactionEmployeeUsername}")
    public ResponseEntity<?> getTransactionStockItemListByEmployee(@PathVariable("transactionEmployeeUsername") String transactionEmployeeUsername) {
        try {
            TransactionEmployeeViewInfo transactionEmployeeViewInfo = transactionEmployeeService.getTransactionEmployeeInformation(transactionEmployeeUsername);

            List<PackageBox> transactionStockPackageBoxList = stockItemService.getTransactionStockItemList(transactionEmployeeViewInfo.getTransactionPointLeader().getUsername());
            return new ResponseEntity<>(transactionStockPackageBoxList, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("list/by/employee/gather/{gatherEmployeeUsername}")
    public ResponseEntity<?> getGatherStockItemListByEmployee(@PathVariable("gatherEmployeeUsername") String gatherEmployeeUsername) {
        try {
            GatherEmployeeViewInfo gatherEmployeeViewInfo = gatherEmployeeService.getGatherEmployeeInformation(gatherEmployeeUsername);

            List<PackageBox> gatherStockPackageBoxList = stockItemService.getGatherStockItemList(gatherEmployeeViewInfo.getGatherPointLeader().getUsername());
            return new ResponseEntity<>(gatherStockPackageBoxList, HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }
}
