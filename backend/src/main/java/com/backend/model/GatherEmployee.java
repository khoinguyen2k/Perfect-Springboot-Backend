package com.backend.model;

import com.backend.dto.signUp.GatherEmployeeSignUpDTO;
import com.backend.dto.update.GatherEmployeeUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "nhan_vien_tap_ket")
public class GatherEmployee {
    @Id
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "ho_ten")
    private String fullName;

    @Column(name = "sdt")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "username_truong_diem_tap_ket", nullable = false)
    private GatherPointLeader gatherPointLeader;

    public GatherEmployee(GatherEmployeeSignUpDTO gatherEmployeeSignUpDTO, GatherPointLeader gatherPointLeader) {
        this.username = gatherEmployeeSignUpDTO.getUsername();
        this.password = gatherEmployeeSignUpDTO.getPassword();
        this.fullName = gatherEmployeeSignUpDTO.getFullName();
        this.phoneNumber = gatherEmployeeSignUpDTO.getPhoneNumber();
        this.gatherPointLeader = gatherPointLeader;
    }

    public void updateInfo(GatherEmployeeUpdateDTO gatherEmployeeUpdateDTO) {
        this.fullName = gatherEmployeeUpdateDTO.getNewFullName();
        this.phoneNumber = gatherEmployeeUpdateDTO.getNewPhoneNumber();
    }
}
