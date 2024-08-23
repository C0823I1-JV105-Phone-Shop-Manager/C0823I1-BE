package com.example.c0823l1_be.repository;

import com.example.c0823l1_be.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierAddEditRepository extends JpaRepository<Supplier,Integer> {

}
