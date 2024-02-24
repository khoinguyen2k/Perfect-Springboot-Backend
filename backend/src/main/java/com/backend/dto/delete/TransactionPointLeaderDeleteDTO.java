package com.backend.dto.delete;

public class TransactionPointLeaderDeleteDTO {
    private String transactionPointLeaderUsername;
    private String gatherPointLeaderUsername;

    public TransactionPointLeaderDeleteDTO() {
    }

    public TransactionPointLeaderDeleteDTO(String transactionPointLeaderUsername, String gatherPointLeaderUsername) {
        this.transactionPointLeaderUsername = transactionPointLeaderUsername;
        this.gatherPointLeaderUsername = gatherPointLeaderUsername;
    }

    public String getTransactionPointLeaderUsername() {
        return transactionPointLeaderUsername;
    }

    public String getGatherPointLeaderUsername() {
        return gatherPointLeaderUsername;
    }
}
