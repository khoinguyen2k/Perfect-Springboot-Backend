package com.backend.dto.delete;

public class TransactionEmployeeDeleteDTO {
    private String transactionEmployeeUsername;
    private String transactionPointLeaderUsername;

    public TransactionEmployeeDeleteDTO() {
    }

    public TransactionEmployeeDeleteDTO(String transactionEmployeeUsername, String transactionPointLeaderUsername) {
        this.transactionEmployeeUsername = transactionEmployeeUsername;
        this.transactionPointLeaderUsername = transactionPointLeaderUsername;
    }

    public String getTransactionEmployeeUsername() {
        return transactionEmployeeUsername;
    }

    public String getTransactionPointLeaderUsername() {
        return transactionPointLeaderUsername;
    }
}
