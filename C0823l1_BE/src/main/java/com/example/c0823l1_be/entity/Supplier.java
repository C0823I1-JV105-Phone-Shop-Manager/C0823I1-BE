package com.example.c0823l1_be.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 10)
    private String uid;

    @Column(nullable = false,length = 150)
    private String name;

    @Column(nullable = false,length = 255)
    private String address;

    @Column(nullable = false,length = 15)
    private String phone;

    @Column(nullable = false,length = 150)
    private String email;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean isDeleted = false;

}
