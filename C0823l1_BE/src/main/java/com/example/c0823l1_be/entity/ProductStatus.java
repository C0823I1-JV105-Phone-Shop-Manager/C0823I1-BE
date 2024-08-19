package com.example.c0823l1_be.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product_status")
@Data
public class ProductStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false)
    private Boolean isDeleted = false;
}
