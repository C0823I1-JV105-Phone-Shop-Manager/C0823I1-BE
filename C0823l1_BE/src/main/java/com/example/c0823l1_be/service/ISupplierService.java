package com.example.c0823l1_be.service;

import com.example.c0823l1_be.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISupplierService {
    Page<Supplier> findAll(Pageable pageable);

    Page<Supplier> searchSuppliers(String search, Pageable pageable);

    void deleteByUids(List<String> uids);

    Page<Supplier> searchByName(String name, Pageable pageable);

    Page<Supplier> searchByAddressAndName(String address, String search, Pageable pageable);

    void softDeleteSuppliersByUids(List<String> uids);
}

