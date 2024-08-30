package com.example.c0823l1_be.repository;

import com.example.c0823l1_be.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierAddEditRepository extends JpaRepository<Supplier,Integer> {
    Integer countAllByUid(String uid);
    Integer countAllByEmail(String email);
    Integer countAllByPhone(String phone);
    Supplier findSupplierByUid(String uid);
    Supplier findSupplierById(Integer id);
    Integer countAllByIdNotAndUid(Integer id, String uid);
    Integer countAllByIdNotAndPhone(Integer id, String phone);
    Integer countAllByIdNotAndEmail(Integer id, String email);
}
