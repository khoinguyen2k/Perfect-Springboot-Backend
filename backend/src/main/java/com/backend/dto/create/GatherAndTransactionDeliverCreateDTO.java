package com.backend.dto.create;

import com.backend.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class GatherAndTransactionDeliverCreateDTO {
    private String gatherEmployeeUsername;
    private String transactionPointLeaderUsername;
    private String state = Constant.onDeliverGatherToTransaction;
    private Integer packageBoxId;
}
