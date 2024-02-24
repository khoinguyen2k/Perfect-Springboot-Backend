package com.backend.dto.viewInfo;

import com.backend.model.TransactionPointLeader;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class TransactionPointLeaderViewInfo {
    private String fullName;
    private String address;

    public TransactionPointLeaderViewInfo(TransactionPointLeader transactionPointLeader) {
        this.fullName = transactionPointLeader.getFullName();
        this.address = transactionPointLeader.getAddress();
    }
}
