package com.example.c0823l1_be.controller;

import com.example.c0823l1_be.dto.SupplierAddDto;
import com.example.c0823l1_be.dto.SupplierUpdateDto;
import com.example.c0823l1_be.entity.ErrorResponse;
import com.example.c0823l1_be.entity.Supplier;
import com.example.c0823l1_be.service.ISupplierAddEditService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class SupplierRestController {
    @Autowired
    private ISupplierAddEditService iSupplierAddEditService;

    @PostMapping("/api/supplier/create")
    public ResponseEntity<?> createSupplier(@RequestBody @Validated SupplierAddDto supplierDto) {
        Supplier supplier = new Supplier();
        Boolean isSaveOK;
        BeanUtils.copyProperties(supplierDto, supplier);
        isSaveOK = iSupplierAddEditService.saveSupplier(supplier);
        if (isSaveOK) {
            return new ResponseEntity<>(HttpStatus.CREATED); //201
        } else {

            return new ResponseEntity<>(HttpStatus.CONFLICT); //409 Xung đột dữ liệu có sẵn
        }
    }

    @PutMapping("/api/supplier/update/{id}")
    public ResponseEntity<?> updateSupplier(@RequestBody @Valid SupplierUpdateDto supplierDto, BindingResult bindingResult, @PathVariable Integer id) {
        System.out.println("4");
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //400 Dữ liệu không hợp lệ
        } else if (id == null || id <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404
        }
        Supplier supplier = new Supplier();
        supplierDto.setId(id);
        BeanUtils.copyProperties(supplierDto, supplier);
        Boolean isSaveOK;
        System.out.println(supplierDto);
        System.out.println(supplier);
        isSaveOK = iSupplierAddEditService.updateSupplier(supplier);
        if (isSaveOK) {
            return new ResponseEntity<>(HttpStatus.CREATED); //201
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT); //409 Xung đột dữ liệu có sẵn
        }
    }

    @GetMapping("/api/supplier/{id}")
    public ResponseEntity<?> findSupplierById(@PathVariable Integer id) {
        SupplierUpdateDto supplierUpdateDto = iSupplierAddEditService.findSupplierById(id);
        if (supplierUpdateDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404
        } else {
            return ResponseEntity.ok(supplierUpdateDto);
        }
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
