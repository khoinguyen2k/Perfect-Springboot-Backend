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
@Table(name = "van_chuyen_giao_dich_tap_ket")
public class TransactionAndGatherDeliver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_don_van_chuyen")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "username_truong_diem_tap_ket", nullable = false)
    private GatherPointLeader gatherPointLeader;

    @ManyToOne
    @JoinColumn(name = "username_truong_diem_giao_dich", nullable = false)
    private TransactionPointLeader transactionPointLeader;

    @Column(name = "trang_thai")
    private String state;

    @Column(name = "thoi_gian_gui", nullable = false)
    private Timestamp sendTime;

    @Column(name = "thoi_gian_nhan")
    private Timestamp receiveTime;

    @ManyToOne
    @JoinColumn(name = "ma_kien_hang", nullable = false)
    private PackageBox packageBox;

    public TransactionAndGatherDeliver(GatherPointLeader gatherPointLeader, TransactionPointLeader transactionPointLeader, String state, Timestamp sendTime, Timestamp receiveTime, PackageBox packageBox) {
        this.gatherPointLeader = gatherPointLeader;
        this.transactionPointLeader = transactionPointLeader;
        this.state = state;
        this.sendTime = sendTime;
        this.receiveTime = receiveTime;
        this.packageBox = packageBox;
    }
}
