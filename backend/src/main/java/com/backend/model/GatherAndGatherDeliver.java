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
@Table(name = "van_chuyen_tap_ket_tap_ket")
public class GatherAndGatherDeliver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_don_van_chuyen")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "username_truong_diem_tap_ket_gui", nullable = false)
    private GatherPointLeader sendGatherPoint;

    @ManyToOne
    @JoinColumn(name = "username_truong_diem_tap_ket_nhan", nullable = false)
    private GatherPointLeader receiveGatherPoint;

    @Column(name = "trang_thai")
    private String state;

    @Column(name = "thoi_gian_gui", nullable = false)
    private Timestamp sendTime;

    @Column(name = "thoi_gian_nhan")
    private Timestamp receiveTime;

    @ManyToOne
    @JoinColumn(name = "ma_kien_hang", nullable = false)
    private PackageBox packageBox;

    public GatherAndGatherDeliver(GatherPointLeader sendGatherPoint, GatherPointLeader receiveGatherPoint, String state, Timestamp sendTime, Timestamp receiveTime, PackageBox packageBox) {
        this.sendGatherPoint = sendGatherPoint;
        this.receiveGatherPoint = receiveGatherPoint;
        this.state = state;
        this.sendTime = sendTime;
        this.receiveTime = receiveTime;
        this.packageBox = packageBox;
    }
}
