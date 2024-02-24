package com.backend.dto.update;

public class TransactionPointLeaderUpdateDTO {
    private String transactionPointLeaderUsername;
    private String newFullName;
    private String newAddress;
    private String gatherPointLeaderUsername;

    public TransactionPointLeaderUpdateDTO() {
    }

    public TransactionPointLeaderUpdateDTO(String transactionPointLeaderUsername, String newFullName, String newAddress, String gatherPointLeaderUsername) {
        this.transactionPointLeaderUsername = transactionPointLeaderUsername;
        this.newFullName = newFullName;
        this.newAddress = newAddress;
        this.gatherPointLeaderUsername = gatherPointLeaderUsername;
    }

    public String getTransactionPointLeaderUsername() {
        return transactionPointLeaderUsername;
    }

    public String getNewFullName() {
        return newFullName;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public String getGatherPointLeaderUsername() {
        return gatherPointLeaderUsername;
    }
}
