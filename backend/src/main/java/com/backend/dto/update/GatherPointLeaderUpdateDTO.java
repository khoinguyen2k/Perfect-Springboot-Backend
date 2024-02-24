package com.backend.dto.update;

public class GatherPointLeaderUpdateDTO {
    private String gatherPointLeaderUsername;
    private String newFullName;
    private String newAddress;
    private String companyLeaderUsername;

    public GatherPointLeaderUpdateDTO() {
    }

    public GatherPointLeaderUpdateDTO(String gatherPointLeaderUsername, String newFullName, String newAddress, String companyLeaderUsername) {
        this.gatherPointLeaderUsername = gatherPointLeaderUsername;
        this.newFullName = newFullName;
        this.newAddress = newAddress;
        this.companyLeaderUsername = companyLeaderUsername;
    }

    public String getGatherPointLeaderUsername() {
        return gatherPointLeaderUsername;
    }

    public String getNewFullName() {
        return newFullName;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public String getCompanyLeaderUsername() {
        return companyLeaderUsername;
    }
}
