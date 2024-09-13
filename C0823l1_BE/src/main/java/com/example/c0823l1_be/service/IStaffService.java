package com.example.c0823l1_be.service;

import com.example.c0823l1_be.dto.StaffViewDTO;
import com.example.c0823l1_be.entity.ProductStatus;
import com.example.c0823l1_be.entity.Staff;

import java.util.List;
import java.util.Optional;

public interface IStaffService {
    public List<Staff> findAll();
    public StaffViewDTO findByUserName(String name);
}
