package com.example.c0823l1_be.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "product_item")
@SoftDelete
@Setter
@Getter
@NoArgsConstructor
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    @Column(nullable = false)
    private String serial;

    @Column(name = "import_date")
    private LocalDate importDate;

    @Column( name = "export_date")
    private LocalDate exportDate;

    @ManyToOne
    @JoinColumn(nullable = false, name = "product_status_id")
    private ProductStatus productStatus;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
