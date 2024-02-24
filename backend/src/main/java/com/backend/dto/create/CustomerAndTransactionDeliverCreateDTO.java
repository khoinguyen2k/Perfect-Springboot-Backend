package com.backend.dto.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerAndTransactionDeliverCreateDTO {
    private String customerUsername;
    private String transactionEmployeeUsername;
    private String state;
    private Integer packageBoxId;
}
