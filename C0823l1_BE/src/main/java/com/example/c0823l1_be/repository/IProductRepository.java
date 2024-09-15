package com.example.c0823l1_be.repository;

import com.example.c0823l1_be.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM product WHERE LOWER(name) LIKE LOWER(CONCAT('%', :name, '%')) AND is_delete = false",
            countQuery = "SELECT COUNT(*) FROM product WHERE LOWER(name) LIKE LOWER(CONCAT('%', :name, '%')) AND is_delete = false",
            nativeQuery = true)
    Page<Product> findProductsByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    @Modifying
    @Query("UPDATE Product p SET p.isDelete = true WHERE p.id = :id")
    void deleteById(@Param("id") Long id);

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
            "b.name AS brand_name ," +
            "p.is_delete " +
            "FROM shop_management.product p " +
            "JOIN shop_management.brand b ON p.brand_id = b.id " +
            "WHERE (:price IS NULL OR p.price = :price) " +
            "AND (:cpu IS NULL OR LOWER(p.cpu) LIKE LOWER(CONCAT('%', :cpu, '%'))) " +
            "AND (:camera IS NULL OR LOWER(p.camera) LIKE LOWER(CONCAT('%', :camera, '%'))) " +
            "AND (:storage IS NULL OR p.storage = :storage) " +
            "AND (:brandId IS NULL OR p.brand_id = :brandId) " ,
            nativeQuery = true)
    Page<Product> findProductsByFilters(
            @Param("price") Integer price,
            @Param("cpu") String cpu,
            @Param("camera") String camera,
            @Param("storage") Integer storage,
            @Param("brandId") Integer brandId,
            Pageable pageable);

    @Query(value = "SELECT * FROM shop_management.product p " +
            "JOIN shop_management.brand b ON p.brand_id = b.id " +
            "WHERE (LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.cpu) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.camera) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:price IS NULL OR p.price = :price) " +
            "AND (:cpu IS NULL OR LOWER(p.cpu) LIKE LOWER(CONCAT('%', :cpu, '%'))) " +
            "AND (:camera IS NULL OR LOWER(p.camera) LIKE LOWER(CONCAT('%', :camera, '%'))) " +
            "AND (:storage IS NULL OR p.storage = :storage) " +
            "AND (:brandId IS NULL OR p.brand_id = :brandId) " +
            "AND p.is_delete = false",
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