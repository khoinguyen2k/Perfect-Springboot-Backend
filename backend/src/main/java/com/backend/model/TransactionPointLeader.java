package com.backend.model;

import com.backend.dto.signUp.TransactionPointLeaderSignUpDTO;
import com.backend.dto.update.TransactionPointLeaderUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "truong_diem_giao_dich")
public class TransactionPointLeader {
    @Id
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "ho_ten")
    private String fullName;

    @Column(name = "dia_chi")
    private String address;

    @ManyToOne
    @JoinColumn(name = "username_truong_diem_tap_ket", nullable = false)
    private GatherPointLeader gatherPointLeader;

    public TransactionPointLeader(TransactionPointLeaderSignUpDTO transactionPointLeaderSignUpDTO, GatherPointLeader gatherPointLeader) {
        this.username = transactionPointLeaderSignUpDTO.getUsername();
        this.password = transactionPointLeaderSignUpDTO.getPassword();
        this.fullName = transactionPointLeaderSignUpDTO.getFullName();
        this.address = transactionPointLeaderSignUpDTO.getAddress();
        this.gatherPointLeader = gatherPointLeader;
    }

    public void updateInfo(TransactionPointLeaderUpdateDTO transactionPointLeaderUpdateDTO) {
        this.fullName = transactionPointLeaderUpdateDTO.getNewFullName();
        this.address = transactionPointLeaderUpdateDTO.getNewAddress();
    }
}
