package com.example.c0823l1_be.repository;

import com.example.c0823l1_be.entity.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductStatusRepository extends JpaRepository<ProductStatus,Integer> {
}
