package com.example.c0823l1_be.repository;

import com.example.c0823l1_be.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order,String> {
    <T> List<T> findBy(Class<T> classType);
    <T> T findById(String id, Class<T> classType);
    <T> T findByCustomer_NameContainingIgnoreCase(String name, Class<T> classType);
}
