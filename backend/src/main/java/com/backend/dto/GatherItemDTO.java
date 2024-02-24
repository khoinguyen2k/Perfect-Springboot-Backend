package com.backend.dto;

import com.backend.model.PackageBox;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class GatherItemDTO {
    private PackageBox packageBox;
    private String gatherPointLeaderUsername;

    public GatherItemDTO(PackageBox packageBox, String gatherPointLeaderUsername) {
        this.packageBox = packageBox;
        this.gatherPointLeaderUsername = gatherPointLeaderUsername;
    }

}
