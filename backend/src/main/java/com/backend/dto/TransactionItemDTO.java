package com.backend.dto;

import com.backend.model.PackageBox;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class TransactionItemDTO {
    private PackageBox packageBox;
    private String transactionPointLeaderUsername;

    public TransactionItemDTO(PackageBox packageBox, String transactionPointLeaderUsername) {
        this.packageBox = packageBox;
        this.transactionPointLeaderUsername = transactionPointLeaderUsername;
    }

}
