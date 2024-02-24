package com.backend.dto.confirm;

public class DeliverConfirmByTransactionEmployeeDTO {
    private Integer id;
    private String deliverState;
    private String transactionEmployeeUsername;

    public DeliverConfirmByTransactionEmployeeDTO() {
    }

    public DeliverConfirmByTransactionEmployeeDTO(Integer id, String deliverState, String transactionEmployeeUsername) {
        this.id = id;
        this.deliverState = deliverState;
        this.transactionEmployeeUsername = transactionEmployeeUsername;
    }

    public Integer getId() {
        return id;
    }

    public String getDeliverState() {
        return deliverState;
    }

    public String getTransactionEmployeeUsername() {
        return transactionEmployeeUsername;
    }
}
