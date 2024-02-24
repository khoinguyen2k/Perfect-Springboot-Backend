package com.backend.dto.update;

public class GatherEmployeeUpdateDTO {
    private String gatherEmployeeUsername;
    private String newFullName;
    private String newPhoneNumber;
    private String gatherPointLeaderUsername;

    public GatherEmployeeUpdateDTO() {
    }

    public GatherEmployeeUpdateDTO(String gatherEmployeeUsername, String newFullName, String newPhoneNumber, String gatherPointLeaderUsername) {
        this.gatherEmployeeUsername = gatherEmployeeUsername;
        this.newFullName = newFullName;
        this.newPhoneNumber = newPhoneNumber;
        this.gatherPointLeaderUsername = gatherPointLeaderUsername;
    }

    public String getGatherEmployeeUsername() {
        return gatherEmployeeUsername;
    }

    public String getNewFullName() {
        return newFullName;
    }

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public String getGatherPointLeaderUsername() {
        return gatherPointLeaderUsername;
    }
}
