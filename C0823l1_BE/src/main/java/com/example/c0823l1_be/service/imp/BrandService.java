package com.example.c0823l1_be.service.imp;

import com.example.c0823l1_be.entity.Brand;
import com.example.c0823l1_be.repository.IBrandRepository;
import com.example.c0823l1_be.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandService implements IBrandService {
    @Autowired
    private IBrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
}
