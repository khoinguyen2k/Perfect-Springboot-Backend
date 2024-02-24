package com.backend.utilities;

import com.backend.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class DeliverRoute {
    private String sendCustomerUsername;
    private String receiveCustomerUsername;
    private String firstTransactionPointCode;
    private String firstGatherPointCode;
    private String secondTransactionPointCode;
    private String secondGatherPointCode;
    private Integer packageBoxId;
    private String state;

    public boolean isSuccessful() {
        return state.contains(Constant.SUCCESSFUL);
    }
}
