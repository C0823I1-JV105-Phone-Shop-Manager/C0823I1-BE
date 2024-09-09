package com.example.c0823l1_be.service.imp;

import com.example.c0823l1_be.entity.Product;
import com.example.c0823l1_be.repository.IProductRepository;
import com.example.c0823l1_be.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Page<Product> findProductsByNameContainingIgnoreCase(String name, Pageable pageable) {
        return productRepository.findProductsByNameContainingIgnoreCase(name, pageable);
    }



    @Override
    public Page<Product> findProductsByFilters(Integer price, String cpu, String camera, Integer storage, Integer brandId, Pageable pageable) {
        return productRepository.findProductsByFilters(price, cpu, camera, storage, brandId, pageable);
    }
}
