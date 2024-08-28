package com.example.c0823l1_be.controller;

import com.example.c0823l1_be.dto.CustomerDTO;
import com.example.c0823l1_be.dto.CustomerViewDTO;
import com.example.c0823l1_be.entity.Customer;
import com.example.c0823l1_be.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    ICustomerService customerService;


    @GetMapping("/api/customers")
    public ResponseEntity<?> searchCustomer( @RequestParam(required = false, defaultValue = "", name = "name") String name,
                                         @RequestParam(required = false, defaultValue = "0", name = "page") int page)
{
    Pageable pageable = PageRequest.of(page, 5);
    Page<CustomerViewDTO> customers = customerService.searchByCustomerName(name,pageable,CustomerViewDTO.class);
    return new ResponseEntity<>(customers, HttpStatus.OK);
}

    @PostMapping("/api/customers")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer targetCustomer = new Customer();
        System.out.println(customerDTO);
        BeanUtils.copyProperties(customerDTO,targetCustomer);
        customerService.save(targetCustomer);
        return ResponseEntity.ok(targetCustomer);
    }
}
