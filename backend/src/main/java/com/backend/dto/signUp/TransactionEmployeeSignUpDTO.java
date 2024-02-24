package com.backend.dto.signUp;

import com.backend.model.TransactionEmployee;

public class TransactionEmployeeSignUpDTO {
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String transactionPointLeaderUsername;

    public TransactionEmployeeSignUpDTO() {
    }

    public TransactionEmployeeSignUpDTO(TransactionEmployee transactionEmployee) {
        this.username = transactionEmployee.getUsername();
        this.password = transactionEmployee.getPassword();
        this.fullName = transactionEmployee.getFullName();
        this.phoneNumber = transactionEmployee.getPhoneNumber();
        this.transactionPointLeaderUsername = transactionEmployee.getTransactionPointLeader().getUsername();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTransactionPointLeaderUsername() {
        return transactionPointLeaderUsername;
    }
}
