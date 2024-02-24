package com.backend.dto.signUp;

import com.backend.model.GatherPointLeader;

public class GatherPointLeaderSignUpDTO {
    private String username;
    private String password;
    private String fullName;
    private String address;
    private String companyLeaderUsername;

    public GatherPointLeaderSignUpDTO() {
    }

    public GatherPointLeaderSignUpDTO(GatherPointLeader gatherPointLeader, String companyLeaderUsername) {
        this.username = gatherPointLeader.getUsername();
        this.password = gatherPointLeader.getPassword();
        this.fullName = gatherPointLeader.getFullName();
        this.address = gatherPointLeader.getAddress();
        this.companyLeaderUsername = companyLeaderUsername;
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

    public String getCompanyLeaderUsername() {
        return companyLeaderUsername;
    }
}
