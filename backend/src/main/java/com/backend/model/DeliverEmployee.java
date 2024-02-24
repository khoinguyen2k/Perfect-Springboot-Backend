package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "nhan_vien_giao_hang")
public class DeliverEmployee {
    @Id
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "ho_ten")
    private String fullName;

    @Column(name = "sdt")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "username_truong_diem_giao_dich", nullable = false)
    private TransactionPointLeader transactionPointLeader;

}
