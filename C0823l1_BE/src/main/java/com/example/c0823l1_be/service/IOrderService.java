package com.example.c0823l1_be.service;

import com.example.c0823l1_be.entity.Order;

import java.util.List;

public interface IOrderService {
    public List<Order> findAll();
    public Order findById(String id);
    public void save(Order order);
    public void deleteById(String id);
}
