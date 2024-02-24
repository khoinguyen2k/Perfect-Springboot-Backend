package com.backend.dto.update;

public class TransactionEmployeeUpdateDTO {
    private String transactionEmployeeUsername;
    private String newFullName;
    private String newPhoneNumber;
    private String transactionPointLeaderUsername;

    public TransactionEmployeeUpdateDTO() {
    }

    public TransactionEmployeeUpdateDTO(String transactionEmployeeUsername, String newFullName, String newPhoneNumber, String transactionPointLeaderUsername) {
        this.transactionEmployeeUsername = transactionEmployeeUsername;
        this.newFullName = newFullName;
        this.newPhoneNumber = newPhoneNumber;
        this.transactionPointLeaderUsername = transactionPointLeaderUsername;
    }

    public String getTransactionEmployeeUsername() {
        return transactionEmployeeUsername;
    }

    public String getNewFullName() {
        return newFullName;
    }

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public String getTransactionPointLeaderUsername() {
        return transactionPointLeaderUsername;
    }
}
