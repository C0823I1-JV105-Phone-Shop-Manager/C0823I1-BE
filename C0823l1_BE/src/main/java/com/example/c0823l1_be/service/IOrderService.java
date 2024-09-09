package com.example.c0823l1_be.service;

import com.example.c0823l1_be.dto.OrderViewDTO;
import com.example.c0823l1_be.entity.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    public <T> List<T> findAll(Class<T> classType);
    public <T> T findById(String id, Class<T> classType);
    public Order save(Order order);
    public void deleteById(String id);
    <T> List<T> findBy(Class<T> clazz);
}
