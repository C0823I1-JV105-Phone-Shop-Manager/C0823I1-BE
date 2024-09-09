package com.example.c0823l1_be.entity;

import com.example.c0823l1_be.customId.OrderIdGenerator;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SoftDelete;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@SoftDelete
@Setter
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @GenericGenerator(
            name = "order_seq",
            strategy = "com.example.c0823l1_be.customId.OrderIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = OrderIdGenerator.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(name = OrderIdGenerator.VALUE_PREFIX_PARAMETER, value = "ORDER-"),
                    @org.hibernate.annotations.Parameter(name = OrderIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id" , nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "staff_id" , nullable = false)
    private Staff staff;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ProductItem> productItemList;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdOn;

    public void addProductItem(ProductItem item) {
        productItemList.add(item);
        item.setOrder(this); // Thiết lập order cho product
    }

}
