package com.example.c0823l1_be.controller;

import com.example.c0823l1_be.dto.SupplierDto;
import com.example.c0823l1_be.entity.Supplier;
import com.example.c0823l1_be.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    private ISupplierService iSupplierService;

    @GetMapping("")
    public ResponseEntity<Page<SupplierDto>> showSupplier(@PageableDefault(page = 0, size = 5) Pageable pageable){
        Page<Supplier> suppliers = iSupplierService.findAll(pageable);
        Page<SupplierDto> supplierDTOs = suppliers.map(supplier -> new SupplierDto(
                supplier.getUid(),
                supplier.getName(),
                supplier.getAddress(),
                supplier.getPhone(),
                supplier.getEmail()
        ));
        return ResponseEntity.ok(supplierDTOs);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteSuppliers(@RequestBody List<Integer> ids) {
        try {
            iSupplierService.deleteByIds(ids);
            return ResponseEntity.ok().body("Suppliers deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete suppliers");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<SupplierDto>> searchSuppliers(
            @RequestParam(value = "search", defaultValue = "") String search,
            @PageableDefault(page = 0, size = 5) Pageable pageable) {
        Page<Supplier> suppliers = iSupplierService.searchSuppliers(search, pageable);
        Page<SupplierDto> supplierDTOs = suppliers.map(supplier -> new SupplierDto(
                supplier.getUid(),
                supplier.getName(),
                supplier.getAddress(),
                supplier.getPhone(),
                supplier.getEmail()
        ));
        return ResponseEntity.ok(supplierDTOs);
    }


    @GetMapping("/")
    public ResponseEntity<List<String>> getAllSupplierNames() {
        List<String> supplierNames = iSupplierService.findAllSupplierNames();
        return new ResponseEntity<>(supplierNames, HttpStatus.OK);
    }
}
