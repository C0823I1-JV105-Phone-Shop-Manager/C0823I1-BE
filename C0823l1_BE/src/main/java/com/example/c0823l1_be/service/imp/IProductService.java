package com.example.c0823l1_be.service.imp;

import com.example.c0823l1_be.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


public interface IProductService {
//    Page<Product> findByPriceAndCpuContainingIgnoreCaseAndCameraContainingIgnoreCaseAndStorageAndBrand_NameContainingIgnoreCase(
//            Long price,
//            String cpu,
//            String camera,
//            Integer storage,
//            String brand_name,
//            Pageable pageable);

    Page<Product> findProductsByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Product> findProductsByFilters(Integer price, String cpu, String camera, Integer storage, Integer brandName, Pageable pageable);

}
