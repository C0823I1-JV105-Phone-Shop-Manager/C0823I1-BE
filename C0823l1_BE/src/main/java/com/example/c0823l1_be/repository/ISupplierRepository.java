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

    /**
     * Xóa nhiều nhà cung cấp theo danh sách UID.
     * @param uids Danh sách UID của các nhà cung cấp cần xóa.
     */
    @Modifying
    @Query("DELETE FROM Supplier s WHERE s.uid IN :uids")
    void deleteAllByUid(@Param("uids") List<String> uids);

    /**
     * Tìm nhà cung cấp theo tên với phân trang.
     * @param name Tên nhà cung cấp.
     * @param pageable Thông tin phân trang.
     * @return Danh sách nhà cung cấp khớp với tên và phân trang.
     */
    Page<Supplier> findByName(String name, Pageable pageable);

    /**
     * Tìm kiếm nhà cung cấp theo các trường UID, số điện thoại, hoặc tên với phân trang.
     * @param search Từ khóa tìm kiếm.
     * @param pageable Thông tin phân trang.
     * @return Danh sách nhà cung cấp khớp với từ khóa tìm kiếm và phân trang.
     */
    @Query("SELECT s FROM Supplier s WHERE LOWER(s.uid) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(s.phone) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(s.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Supplier> searchSuppliers(@Param("search") String search, Pageable pageable);

    /**
     * Tìm kiếm nhà cung cấp theo tên hoặc số điện thoại với phân trang.
     * @param searchTerm Từ khóa tìm kiếm (tên hoặc số điện thoại).
     * @param pageable Thông tin phân trang.
     * @return Danh sách nhà cung cấp khớp với từ khóa tìm kiếm và phân trang.
     */
    @Query("SELECT s FROM Supplier s WHERE s.name LIKE %:searchTerm% OR s.phone LIKE %:searchTerm%")
    Page<Supplier> searchByNameOrPhone(@Param("searchTerm") String searchTerm, Pageable pageable);

    /**
     * Tìm nhà cung cấp theo địa chỉ và từ khóa tìm kiếm với phân trang.
     * @param address Địa chỉ của nhà cung cấp.
     * @param searchTerm Từ khóa tìm kiếm (tên hoặc số điện thoại).
     * @param pageable Thông tin phân trang.
     * @return Danh sách nhà cung cấp khớp với điều kiện địa chỉ và từ khóa tìm kiếm, cùng phân trang.
     */
    @Query("SELECT s FROM Supplier s WHERE s.address LIKE %:address% AND (s.name LIKE %:searchTerm% OR s.phone LIKE %:searchTerm%)")
    Page<Supplier> findByAddressContainingAndNameOrPhone(@Param("address") String address, @Param("searchTerm") String searchTerm, Pageable pageable);

}

