package com.example.c0823l1_be.service;

import com.example.c0823l1_be.entity.Order;
import com.example.c0823l1_be.entity.ProductItem;

import java.util.List;

public interface IProductItemService {
    public <T> List<T> findAll(Class<T> classType);
    public <T> T findById(int id, Class<T> classType);
    public void save(ProductItem productItem);
    public void deleteById(int id);
    <T> T searchByProductName(String name, Class<T> classType);
}
