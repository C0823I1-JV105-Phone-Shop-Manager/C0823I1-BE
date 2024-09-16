package com.example.c0823l1_be.service;

import com.example.c0823l1_be.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISupplierService {
    // Tìm tất cả nhà cung cấp
    Page<Supplier> findAll(Pageable pageable);

    // Tìm kiếm theo từ khóa (name, phone,...)
    Page<Supplier> searchSuppliers(String search, Pageable pageable);

    // Xóa nhiều nhà cung cấp theo danh sách UID
    void deleteByUids(List<String> uids);

    // Tìm kiếm theo tên
    Page<Supplier> searchByName(String name, Pageable pageable);

    // Thêm phương thức mới để lọc theo địa chỉ và tên
    Page<Supplier> searchByAddressAndName(String address, String search, Pageable pageable);
}

