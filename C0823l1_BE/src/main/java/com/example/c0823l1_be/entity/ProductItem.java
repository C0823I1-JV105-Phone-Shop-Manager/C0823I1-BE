package com.example.c0823l1_be.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "product_item")
@Data
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    @Column(nullable = false)
    private String serial;

    @Column(nullable = false, name = "import_date")
    private Date importDate;

    @Column(nullable = false, name = "export_date")
    private Date exportDate;

    @ManyToOne
    @JoinColumn(nullable = false, name = "product_status_id")
    private ProductStatus productStatus;

    @Column(nullable = false)
    private Boolean deleted = false;

    @ManyToOne
    @JoinColumn(nullable = false, name = "order_id")
    private Order order;
}
