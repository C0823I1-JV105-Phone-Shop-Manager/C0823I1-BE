package com.example.c0823l1_be.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 150)
    private String name;

}
