package com.example.c0823l1_be.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,length = 30)
    private String phone;

    @Column(nullable = false,length = 100)
    private String email;

    @Column(nullable = false)
    private Date bitrthdate;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false,name = "register_date")
    private Date registerdate;

    @Column(nullable = false)
    private boolean deleted = false;

}
