package com.example.c0823l1_be.repository;

import com.example.c0823l1_be.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findProductsByNameContainingIgnoreCase(String name, Pageable pageable);
    void deleteById(Long id);

    // Truy vấn tìm kiếm sản phẩm theo các bộ lọc: price, cpu, camera, storage, brandId
    @Query(value = "SELECT " +
            "p.id, " +
            "p.camera, " +
            "p.cpu, " +
            "p.description, " +
            "p.image_url, " +
            "p.name, " +
            "p.price, " +
            "p.screen_size, " +
            "p.selfie_camera, " +
            "p.storage, " +
            "b.id AS brand_id, " +
            "b.name AS brand_name " +
            "FROM shop_management.product p " +
            "JOIN shop_management.brand b ON p.brand_id = b.id " +
            "WHERE (:price IS NULL OR p.price = :price) " +
            "AND (:cpu IS NULL OR LOWER(p.cpu) LIKE LOWER(CONCAT('%', :cpu, '%'))) " +
            "AND (:camera IS NULL OR LOWER(p.camera) LIKE LOWER(CONCAT('%', :camera, '%'))) " +
            "AND (:storage IS NULL OR p.storage = :storage) " +
            "AND (:brandId IS NULL OR p.brand_id = :brandId) ",
            nativeQuery = true)
    Page<Product> findProductsByFilters(
            @Param("price") Integer price,
            @Param("cpu") String cpu,
            @Param("camera") String camera,
            @Param("storage") Integer storage,
            @Param("brandId") Integer brandId,
            Pageable pageable);

    // Tìm kiếm sản phẩm bằng từ khóa gần đúng trong name, cpu, hoặc camera
    @Query(value = "SELECT * FROM shop_management.product p " +
            "JOIN shop_management.brand b ON p.brand_id = b.id " +
            "WHERE (LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.cpu) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.camera) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:price IS NULL OR p.price = :price) " +
            "AND (:cpu IS NULL OR LOWER(p.cpu) LIKE LOWER(CONCAT('%', :cpu, '%'))) " +
            "AND (:camera IS NULL OR LOWER(p.camera) LIKE LOWER(CONCAT('%', :camera, '%'))) " +
            "AND (:storage IS NULL OR p.storage = :storage) " +
            "AND (:brandId IS NULL OR p.brand_id = :brandId) ",
            nativeQuery = true)
    Page<Product> searchByKeywordWithFilters(
            @Param("keyword") String keyword,
            @Param("price") Integer price,
            @Param("cpu") String cpu,
            @Param("camera") String camera,
            @Param("storage") Integer storage,
            @Param("brandId") Integer brandId,
            Pageable pageable);
}
























//    @Query(value = "SELECT " +
//            "p.id, " +
//            "p.camera, " +
//            "p.cpu, " +
//            "p.description, " +
//            "p.image_url, " +
//            "p.name, " +
//            "p.price, " +
//            "p.screen_size, " +
//            "p.selfie_camera, " +
//            "p.storage, " +
//            "b.id AS brand_id, " +
//            "b.name AS brand_name " +
//            "FROM shop_management.product p " +
//            "JOIN shop_management.brand b ON p.brand_id = b.id " +
//            "WHERE p.price = :price " +
//            "AND LOWER(p.cpu) LIKE LOWER(CONCAT('%', :cpu, '%')) " +
//            "AND LOWER(p.camera) LIKE LOWER(CONCAT('%', :camera, '%')) " +
//            "AND p.storage = :storage " +
//            "AND LOWER(b.name) LIKE LOWER(CONCAT('%', :brandName, '%'))", nativeQuery = true)