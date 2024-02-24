package com.backend.dto.viewInfo;

import com.backend.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class CustomerViewInfo {
    private String fullName;
    private String address;
    private String phoneNumber;
    private Integer postalCode;

    public CustomerViewInfo(Customer customer) {
        this.fullName = customer.getFullName();
        this.address = customer.getAddress();
        this.phoneNumber = customer.getPhoneNumber();
        this.postalCode = customer.getPostalCode();
    }
}
