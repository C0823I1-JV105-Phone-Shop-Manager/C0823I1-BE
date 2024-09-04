package com.example.c0823l1_be.repository;

import com.example.c0823l1_be.entity.ProductItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductItemRepository extends JpaRepository<ProductItem,Integer> {
    <T> List<T> findBy(Class<T> classType);
    <T> T findById(Integer id, Class<T> classType);
    <T> Page<T> findByProduct_NameContainingIgnoreCaseAndProductStatus_Id(String name,int id, Pageable pageable, Class<T> classType);
}
