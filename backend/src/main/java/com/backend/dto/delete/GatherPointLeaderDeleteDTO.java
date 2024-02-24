package com.backend.dto.delete;

public class GatherPointLeaderDeleteDTO {
    private String gatherPointLeaderUsername;
    private String companyLeaderUsername;

    public GatherPointLeaderDeleteDTO() {
    }

    public GatherPointLeaderDeleteDTO(String gatherPointLeaderUsername, String companyLeaderUsername) {
        this.gatherPointLeaderUsername = gatherPointLeaderUsername;
        this.companyLeaderUsername = companyLeaderUsername;
    }

    public String getGatherPointLeaderUsername() {
        return gatherPointLeaderUsername;
    }

    public String getCompanyLeaderUsername() {
        return companyLeaderUsername;
    }
}
