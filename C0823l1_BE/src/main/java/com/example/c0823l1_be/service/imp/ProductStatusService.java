package com.example.c0823l1_be.service.imp;

import com.example.c0823l1_be.entity.ProductStatus;
import com.example.c0823l1_be.repository.IProductStatusRepository;
import com.example.c0823l1_be.service.IProductStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductStatusService implements IProductStatusService {
    @Autowired
    private IProductStatusRepository productStatusRepository;
    @Override
    public List<ProductStatus> findAll() {
        return productStatusRepository.findAll();
    }

    @Override
    public Optional<ProductStatus> findById(int id) {
        return productStatusRepository.findById(id);
    }
}
