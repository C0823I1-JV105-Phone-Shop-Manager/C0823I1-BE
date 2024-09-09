package com.example.c0823l1_be.service;

import com.example.c0823l1_be.entity.Order;
import com.example.c0823l1_be.entity.ProductItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductItemService {
    public <T> List<T> findAll(Class<T> classType);
    public <T> T findById(int id, Class<T> classType);
    public void save(ProductItem productItem);
    public void deleteById(int id);
    <T> Page<T> searchByProductName(String name, Pageable pageable, Class<T> classType);
}
