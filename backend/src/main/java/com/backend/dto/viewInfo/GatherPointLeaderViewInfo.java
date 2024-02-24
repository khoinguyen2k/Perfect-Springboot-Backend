package com.backend.dto.viewInfo;

import com.backend.model.GatherPointLeader;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class GatherPointLeaderViewInfo {
    private String fullName;
    private String address;

    public GatherPointLeaderViewInfo(GatherPointLeader gatherPointLeader) {
        this.fullName = gatherPointLeader.getFullName();
        this.address = gatherPointLeader.getAddress();
    }
}
