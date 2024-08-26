package com.example.c0823l1_be.repository;

import com.example.c0823l1_be.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IOrderRepository extends JpaRepository<Order,String> {
    Optional<Order> findById(String id);
}
