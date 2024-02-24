package com.backend.dto.signUp;

import com.backend.model.GatherEmployee;

public class GatherEmployeeSignUpDTO {
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String gatherPointLeaderUsername;

    public GatherEmployeeSignUpDTO() {
    }

    public GatherEmployeeSignUpDTO(GatherEmployee gatherEmployee, String gatherPointLeaderUsername) {
        this.username = gatherEmployee.getUsername();
        this.password = gatherEmployee.getPassword();
        this.fullName = gatherEmployee.getFullName();
        this.phoneNumber = gatherEmployee.getPhoneNumber();
        this.gatherPointLeaderUsername = gatherPointLeaderUsername;
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

    public String getGatherPointLeaderUsername() {
        return gatherPointLeaderUsername;
    }
}
