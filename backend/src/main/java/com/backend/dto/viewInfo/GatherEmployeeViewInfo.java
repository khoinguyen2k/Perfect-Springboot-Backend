package com.backend.dto.viewInfo;

import com.backend.model.GatherEmployee;
import com.backend.model.GatherPointLeader;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class GatherEmployeeViewInfo {
    private String fullName;
    private String phoneNumber;
    private GatherPointLeader gatherPointLeader;

    public GatherEmployeeViewInfo(GatherEmployee gatherEmployee) {
        this.fullName = gatherEmployee.getFullName();
        this.phoneNumber = gatherEmployee.getPhoneNumber();
        this.gatherPointLeader = gatherEmployee.getGatherPointLeader();
    }
}
