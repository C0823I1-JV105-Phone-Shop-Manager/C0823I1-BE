package com.example.c0823l1_be.service;

import com.example.c0823l1_be.entity.ProductStatus;

import java.util.List;
import java.util.Optional;

public interface IProductStatusService {
    public List<ProductStatus> findAll();
    public Optional<ProductStatus> findById(int id);
}
