package com.example.c0823l1_be.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "product_item_order")
@Data
public class ProductItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "product_item_id")
    private ProductItem productItem;

    @ManyToOne
    @JoinColumn(nullable = false, name = "order_id")
    private Order order;
}