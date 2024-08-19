package com.example.c0823l1_be.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(nullable = false,length = 150)
    private String name;

    @Column(nullable = false,length = 50)
    private String cpu;

    @Column(nullable = false)
    private Integer storage;

    @Column(nullable = false,length = 255, name = "image_url")
    private String image;

    @Column(nullable = false)
    private Float screenSize;

    @Column(nullable = false,length = 50)
    private String camera;

    @Column(nullable = false,length = 50,name = "selfie_camera")
    private String selfieCamera;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private String description;

}
