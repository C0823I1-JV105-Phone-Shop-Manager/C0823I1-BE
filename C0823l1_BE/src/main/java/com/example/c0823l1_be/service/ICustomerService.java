package com.example.c0823l1_be.service;

import com.example.c0823l1_be.entity.Customer;
import com.example.c0823l1_be.entity.ProductItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    public <T> List<T> findAll(Class<T> classType);
    public <T> T findById(int id, Class<T> classType);
    public Customer save(Customer customer);
    public void deleteById(int id);
    <T> List<T> searchByCustomerName(String name,  Class<T> classType);

    <T> T findByEmail(String email);
}
