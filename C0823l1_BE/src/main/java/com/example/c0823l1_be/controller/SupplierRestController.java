package com.example.c0823l1_be.controller;

import com.example.c0823l1_be.dto.SupplierDto;
import com.example.c0823l1_be.entity.Supplier;
import com.example.c0823l1_be.service.ISupplierAddEditService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SupplierAddEditController {
    @Autowired
    private ISupplierAddEditService iSupplierAddEditService;

    @PostMapping("/api/create")
    public ResponseEntity<?> createSupplier(@RequestBody SupplierDto supplierDto){
        Supplier supplier = new Supplier();
        Boolean isSaveOK = false;
        BeanUtils.copyProperties(supplierDto, supplier);
        isSaveOK =iSupplierAddEditService.saveSupplier(supplier);
        if (isSaveOK == true) {
            return new ResponseEntity<>(HttpStatus.CREATED); //Thành công và tạo được data
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
