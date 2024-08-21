package com.example.c0823l1_be.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "supplier_product")
public class SupplierProduct {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "supplier_id" , nullable = false)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "product_id" , nullable = false)
    private Product product;
}
