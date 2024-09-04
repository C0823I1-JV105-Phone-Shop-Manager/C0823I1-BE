package com.example.c0823l1_be.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 30)
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }
}
