package com.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "lanh_dao_cong_ty")
public class CompanyLeader {
    @Id
    private String username;

    @Column(nullable = false)
    private String password;

    public CompanyLeader(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
