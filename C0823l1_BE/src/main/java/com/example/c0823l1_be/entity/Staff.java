package com.example.c0823l1_be.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table
@Data
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @Column(nullable = false,name = "full_name",length = 200)
    private String fullName;

    @Column(nullable = false,length = 200)
    private String address;

    @Column(nullable = false)
    private Date dob;

    @Column(nullable = false,length = 10,name = "phone_number")
    private String phoneNumber;

}
