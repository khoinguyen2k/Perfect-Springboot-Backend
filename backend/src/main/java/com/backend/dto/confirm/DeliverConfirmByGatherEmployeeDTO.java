package com.backend.dto.confirm;

public class DeliverConfirmByGatherEmployeeDTO {
    private Integer id;
    private String deliverState;
    private String gatherEmployeeUsername;

    public DeliverConfirmByGatherEmployeeDTO() {
    }

    public DeliverConfirmByGatherEmployeeDTO(Integer id, String deliverState, String gatherEmployeeUsername) {
        this.id = id;
        this.deliverState = deliverState;
        this.gatherEmployeeUsername = gatherEmployeeUsername;
    }

    public Integer getId() {
        return id;
    }

    public String getDeliverState() {
        return deliverState;
    }

    public String getGatherEmployeeUsername() {
        return gatherEmployeeUsername;
    }
}
