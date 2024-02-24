package com.backend.model;

import com.backend.dto.signUp.GatherPointLeaderSignUpDTO;
import com.backend.dto.update.GatherPointLeaderUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "truong_diem_tap_ket")
public class GatherPointLeader {
    @Id
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "ho_ten")
    private String fullName;

    @Column(name = "dia_chi")
    private String address;

    public GatherPointLeader(GatherPointLeaderSignUpDTO gatherPointLeaderSignUpDTO) {
        this.username = gatherPointLeaderSignUpDTO.getUsername();
        this.password = gatherPointLeaderSignUpDTO.getPassword();
        this.fullName = gatherPointLeaderSignUpDTO.getFullName();
        this.address = gatherPointLeaderSignUpDTO.getAddress();
    }

    public void updateInfo(GatherPointLeaderUpdateDTO gatherPointLeaderUpdateDTO) {
        this.fullName = gatherPointLeaderUpdateDTO.getNewFullName();
        this.address = gatherPointLeaderUpdateDTO.getNewAddress();
    }
}
