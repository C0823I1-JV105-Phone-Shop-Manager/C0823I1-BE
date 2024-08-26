package com.example.c0823l1_be.entity;

import com.example.c0823l1_be.customId.OrderIdGenerator;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "orders")
@Data
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

    @Column(nullable = false,name = "create_on")
    private Date createOn;

    @Column(nullable = false)
    private boolean deleted = false;

}
