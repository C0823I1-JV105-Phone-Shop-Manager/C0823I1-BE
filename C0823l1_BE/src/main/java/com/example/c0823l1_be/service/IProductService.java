package com.example.c0823l1_be.service;

import com.example.c0823l1_be.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductService {

    // Tìm sản phẩm theo tên (có phân trang)
    Page<Product> findProductsByNameContainingIgnoreCase(String name, Pageable pageable);

    // Tìm sản phẩm theo các bộ lọc
    Page<Product> findProductsByFilters(Integer price, String cpu, String camera, Integer storage, Integer brandId, Pageable pageable);

    // Tìm sản phẩm theo từ khóa và các bộ lọc
    Page<Product> findProductsByFiltersAndKeyword(Integer price, String cpu, String camera, Integer storage, Integer brandId, String keyword, Pageable pageable);

    // Xóa sản phẩm theo ID
    void deleteProductById(Long id);

    // Tìm sản phẩm theo ID
    Optional<Product> findProductById(Long id);
}
















































//package com.example.c0823l1_be.service;
//
//import com.example.c0823l1_be.entity.Product;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//
//public interface IProductService {
//
//
//    Page<Product> findProductsByNameContainingIgnoreCase(String name, Pageable pageable);
//
//    Page<Product> findProductsByFilters(Integer price, String cpu, String camera, Integer storage, Integer brandName, Pageable pageable);
//    // Search products by a general keyword in name, cpu, or camera
//    Page<Product> findProductsByFiltersAndKeyword(Integer price, String cpu, String camera, Integer storage, Integer brandId, String keyword, Pageable pageable);
//
//    // Delete product by ID
//    void deleteProductById(Long id);
//    // New method to find product by ID
////    Optional<Product> findProductById(Long id);
//
//}
////    Page<Product> findByPriceAndCpuContainingIgnoreCaseAndCameraContainingIgnoreCaseAndStorageAndBrand_NameContainingIgnoreCase(
////            Long price,
////            String cpu,
////            String camera,
////            Integer storage,
////            String brand_name,
////            Pageable pageable);