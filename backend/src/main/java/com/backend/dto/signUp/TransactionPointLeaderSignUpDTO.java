package com.backend.dto.signUp;

import com.backend.model.TransactionPointLeader;

public class TransactionPointLeaderSignUpDTO {
    private String username;
    private String password;
    private String fullName;
    private String address;
    private String gatherPointLeaderUsername;

    public TransactionPointLeaderSignUpDTO() {
    }

    public TransactionPointLeaderSignUpDTO(TransactionPointLeader transactionPointLeader) {
        this.username = transactionPointLeader.getUsername();
        this.password = transactionPointLeader.getPassword();
        this.fullName = transactionPointLeader.getFullName();
        this.address = transactionPointLeader.getAddress();
        this.gatherPointLeaderUsername = transactionPointLeader.getGatherPointLeader().getUsername();
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

    public String getAddress() {
        return address;
    }

    public String getGatherPointLeaderUsername() {
        return gatherPointLeaderUsername;
    }
}
