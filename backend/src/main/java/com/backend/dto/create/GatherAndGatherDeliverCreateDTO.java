package com.backend.dto.create;

import com.backend.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class GatherAndGatherDeliverCreateDTO {
    private String sendGatherEmployeeUsername;
    private String receiveGatherPointUsername;
    private String state = Constant.onDeliverToOtherGather;
    private Integer packageBoxId;
}
