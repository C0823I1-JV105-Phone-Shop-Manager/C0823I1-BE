package com.example.c0823l1_be.controller;

import com.example.c0823l1_be.dto.CustomerDTO;
import com.example.c0823l1_be.dto.CustomerViewDTO;
import com.example.c0823l1_be.dto.StaffViewDTO;
import com.example.c0823l1_be.entity.Customer;
import com.example.c0823l1_be.entity.Staff;
import com.example.c0823l1_be.repository.IOrderRepository;
import com.example.c0823l1_be.service.ICustomerService;
import com.example.c0823l1_be.service.IStaffService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@CrossOrigin(origins = "*")
public class StaffController {

    @Autowired
    IStaffService staffService;

    @GetMapping("/api/staffs")
    public ResponseEntity<?> findStaffByName( @RequestParam(required = false, defaultValue = "", name = "name") String name)
{

    StaffViewDTO staff = staffService.findByUserName(name,StaffViewDTO.class);
    return new ResponseEntity<>(staff, HttpStatus.OK);
}
}
