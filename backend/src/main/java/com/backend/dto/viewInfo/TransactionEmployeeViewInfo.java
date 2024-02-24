package com.backend.dto.viewInfo;

import com.backend.model.TransactionEmployee;
import com.backend.model.TransactionPointLeader;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class TransactionEmployeeViewInfo {
    private String fullName;
    private String phoneNumber;
    private TransactionPointLeader transactionPointLeader;

    public TransactionEmployeeViewInfo(TransactionEmployee transactionEmployee) {
        this.fullName = transactionEmployee.getFullName();
        this.phoneNumber = transactionEmployee.getPhoneNumber();
        this.transactionPointLeader = transactionEmployee.getTransactionPointLeader();
    }
}
