package com.example.c0823l1_be.service.imp;

import com.example.c0823l1_be.dto.StaffViewDTO;
import com.example.c0823l1_be.entity.ProductStatus;
import com.example.c0823l1_be.entity.Staff;
import com.example.c0823l1_be.repository.IProductStatusRepository;
import com.example.c0823l1_be.repository.StaffRepository;
import com.example.c0823l1_be.service.IProductStatusService;
import com.example.c0823l1_be.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService implements IStaffService {
    @Autowired
    private StaffRepository staffRepository;


    @Override
    public List<Staff> findAll() {
        return List.of();
    }

    @Override
    public StaffViewDTO findByUserName(String name) {
        return staffRepository.findByUser_Username(name,StaffViewDTO.class);
    }
}
