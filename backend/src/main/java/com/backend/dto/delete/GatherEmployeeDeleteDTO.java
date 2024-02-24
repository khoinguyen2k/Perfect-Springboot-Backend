package com.backend.dto.delete;

public class GatherEmployeeDeleteDTO {
    private String gatherEmployeeUsername;
    private String gatherPointLeaderUsername;

    public GatherEmployeeDeleteDTO() {
    }

    public GatherEmployeeDeleteDTO(String gatherEmployeeUsername, String gatherPointLeaderUsername) {
        this.gatherEmployeeUsername = gatherEmployeeUsername;
        this.gatherPointLeaderUsername = gatherPointLeaderUsername;
    }

    public String getGatherEmployeeUsername() {
        return gatherEmployeeUsername;
    }

    public String getGatherPointLeaderUsername() {
        return gatherPointLeaderUsername;
    }
}
