package com.example.c0823l1_be.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table
@SoftDelete
@Setter
@Getter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,length = 30)
    private String phone;

    @Column(nullable = false,length = 100)
    private String email;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private String address;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp registerDate;
}
