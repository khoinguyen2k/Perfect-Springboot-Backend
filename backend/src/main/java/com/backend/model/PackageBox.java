package com.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "kien_hang")
public class PackageBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_kien_hang")
    private Integer id;

    @Column(name = "mo_ta")
    private String description;

    @Column(name = "loai_hang")
    private String itemType;

    @Column(name = "khoi_luong")
    private Integer weight;

    @Column(name = "image_url")
    private String imageUrl;

    public PackageBox(String description, String itemType, Integer weight, String imageUrl) {
        this.description = description;
        this.itemType = itemType;
        this.weight = weight;
        this.imageUrl = imageUrl;
    }

}
