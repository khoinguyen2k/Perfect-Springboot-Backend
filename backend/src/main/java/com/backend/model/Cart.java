package com.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "don_hang")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_don_hang")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "username_nguoi_gui", nullable = false)
    private Customer sendCustomer;

    @ManyToOne
    @JoinColumn(name = "username_nguoi_nhan", nullable = false)
    private Customer receiveCustomer;

    @OneToOne
    @JoinColumn(name = "ma_kien_hang", nullable = false)
    private PackageBox packageBox;

    @Column(name = "thoi_gian_gui", nullable = false)
    private Timestamp sendTime;

    @Column(name = "thoi_gian_nhan")
    private Timestamp receiveTime;

    @Column(name = "trang_thai")
    private String state;

    @Column(name = "gia_tien", nullable = false)
    private Integer price;

    public Cart(Customer sendCustomer, Customer receiveCustomer, PackageBox packageBox, Timestamp sendTime, Timestamp receiveTime, String state, Integer price) {
        this.sendCustomer = sendCustomer;
        this.receiveCustomer = receiveCustomer;
        this.packageBox = packageBox;
        this.sendTime = sendTime;
        this.receiveTime = receiveTime;
        this.state = state;
        this.price = price;
    }
}
