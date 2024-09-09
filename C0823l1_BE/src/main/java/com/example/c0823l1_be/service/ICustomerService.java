package com.example.c0823l1_be.service;

import com.example.c0823l1_be.entity.Customer;
import com.example.c0823l1_be.entity.ProductItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    public <T> List<T> findAll(Class<T> classType);
    public <T> T findById(int id, Class<T> classType);
    public void save(Customer customer);
    public void deleteById(int id);
    <T> Page<T> searchByCustomerName(String name, Pageable pageable, Class<T> classType);
}
