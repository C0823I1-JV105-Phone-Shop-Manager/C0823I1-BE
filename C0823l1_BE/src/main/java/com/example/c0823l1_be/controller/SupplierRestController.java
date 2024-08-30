package com.example.c0823l1_be.controller;

import com.example.c0823l1_be.dto.SupplierAddDto;
import com.example.c0823l1_be.dto.SupplierUpdateDto;
import com.example.c0823l1_be.entity.Supplier;
import com.example.c0823l1_be.service.ISupplierAddEditService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class SupplierRestController {
    @Autowired
    private ISupplierAddEditService iSupplierAddEditService;

    @PostMapping("/api/supplier/create")
    public ResponseEntity<?> createSupplier(@RequestBody @Validated SupplierAddDto supplierDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError());
            return new ResponseEntity<>(HttpStatus.CONFLICT); //409 Xung đột dữ liệu có sẵn
        }
        Supplier supplier = new Supplier();
        Boolean isSaveOK;
        BeanUtils.copyProperties(supplierDto, supplier);
        isSaveOK = iSupplierAddEditService.saveSupplier(supplier);
        if (isSaveOK) {
            return new ResponseEntity<>(HttpStatus.CREATED); //201
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY); //422 Không xác định được đối tượng
        }
    }

    @PostMapping("/api/supplier/update/{id}")
    public ResponseEntity<?> updateSupplier(@RequestBody @Validated SupplierUpdateDto supplierDto, BindingResult bindingResult,@PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //409 Xung đột dữ liệu có sẵn
        } else if (id == null ||  id <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404
        }
        Supplier supplier = new Supplier();
        supplierDto.setId(id);
        BeanUtils.copyProperties(supplierDto, supplier);
        Boolean isSaveOK;
        isSaveOK = iSupplierAddEditService.updateSupplier(supplier);
        if (isSaveOK) {
            return new ResponseEntity<>(HttpStatus.CREATED); //201
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY); //422 Không xác định được đối tượng
        }
    }

}
