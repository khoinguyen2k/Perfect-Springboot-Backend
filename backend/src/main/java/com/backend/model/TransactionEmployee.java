package com.backend.model;

import com.backend.dto.signUp.TransactionEmployeeSignUpDTO;
import com.backend.dto.update.TransactionEmployeeUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "nhan_vien_giao_dich")
public class TransactionEmployee {
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

    public TransactionEmployee(TransactionEmployeeSignUpDTO transactionEmployeeSignUpDTO, TransactionPointLeader transactionPointLeader) {
        this.username = transactionEmployeeSignUpDTO.getUsername();
        this.password = transactionEmployeeSignUpDTO.getPassword();
        this.fullName = transactionEmployeeSignUpDTO.getFullName();
        this.phoneNumber = transactionEmployeeSignUpDTO.getPhoneNumber();
        this.transactionPointLeader = transactionPointLeader;
    }

    public void updateInfo(TransactionEmployeeUpdateDTO transactionEmployeeUpdateDTO) {
        this.fullName = transactionEmployeeUpdateDTO.getNewFullName();
        this.phoneNumber = transactionEmployeeUpdateDTO.getNewPhoneNumber();
    }
}
