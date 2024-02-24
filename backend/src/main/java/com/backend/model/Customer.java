package com.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "khach_hang")
public class Customer {
    @Id
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "ho_ten")
    private String fullName;

    @Column(name = "dia_chi")
    private String address;

    @Column(name = "sdt")
    private String phoneNumber;

    @Column(name = "ma_buu_chinh")
    private Integer postalCode;

}
