package com.example.c0823l1_be.repository;

import com.example.c0823l1_be.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Integer> {
    @Modifying
    @Query("DELETE FROM Supplier s WHERE s.id IN :ids")
    void deleteAllById(@Param("ids") List<Integer> ids);
    Page<Supplier> findByName(String name, Pageable pageable);

    @Query("SELECT s FROM Supplier s WHERE LOWER(s.uid) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(s.phone) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(s.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Supplier> searchSuppliers(@Param("search") String search, Pageable pageable);
}
