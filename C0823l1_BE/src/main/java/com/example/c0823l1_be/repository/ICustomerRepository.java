package com.example.c0823l1_be.repository;

import com.example.c0823l1_be.entity.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Integer> {
    <T> List<T> findBy(Class<T> classType);
    <T> T findById(Integer id, Class<T> classType);
    <T> List<T> findByNameContainingIgnoreCase(String name,  Class<T> classType);
}
