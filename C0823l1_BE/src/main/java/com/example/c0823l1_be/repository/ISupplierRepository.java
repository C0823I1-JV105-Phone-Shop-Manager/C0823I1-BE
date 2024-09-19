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
    @Query("UPDATE Supplier s SET s.isDeleted = true WHERE s.uid IN :uids")
    void softDeleteByUids(@Param("uids") List<String> uids);

    Page<Supplier> findByNameAndIsDeletedFalse(String name, Pageable pageable);

    @Query("SELECT s FROM Supplier s WHERE s.isDeleted = false AND (" +
            "LOWER(s.uid) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(s.phone) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(s.name) LIKE LOWER(CONCAT('%', :search, '%'))) ")
    Page<Supplier> searchSuppliers(@Param("search") String search, Pageable pageable);

    @Query("SELECT s FROM Supplier s WHERE s.isDeleted = false AND s.address LIKE %:address% AND (s.name LIKE %:searchTerm% OR s.phone LIKE %:searchTerm%)")
    Page<Supplier> findByAddressContainingAndNameOrPhone(@Param("address") String address, @Param("searchTerm") String searchTerm, Pageable pageable);

    @Modifying
    @Query("DELETE FROM Supplier s WHERE s.uid IN :uids")
    void deleteAllByUid(@Param("uids") List<String> uids);

    @Query("SELECT s FROM Supplier s WHERE s.isDeleted = false")
    Page<Supplier> findAllByIsDeletedFalse(Pageable pageable);

    List<Supplier> findAllByUidInAndIsDeletedFalse(List<String> uids);
}
