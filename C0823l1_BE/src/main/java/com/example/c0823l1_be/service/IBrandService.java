package com.example.c0823l1_be.service;

import com.example.c0823l1_be.entity.Brand;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IBrandService {
    List<Brand> getAllBrands();
}
