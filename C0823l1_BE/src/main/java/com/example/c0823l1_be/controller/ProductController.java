package com.example.c0823l1_be.controller;

import com.example.c0823l1_be.entity.Brand;
import com.example.c0823l1_be.entity.Product;
import com.example.c0823l1_be.service.IBrandService;
import com.example.c0823l1_be.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin

public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IBrandService brandService;

    @GetMapping("/products")
    public ResponseEntity<?> showAllProducts(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "") String name) {
        try {
            Pageable pageable = PageRequest.of(page, 8);
            Page<Product> products = productService.findProductsByNameContainingIgnoreCase(name, pageable);

            if (products.isEmpty()) {
                return new ResponseEntity<>("No products found", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching products", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filters")
    public ResponseEntity<?> filterProducts(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "") Integer price,
            @RequestParam(required = false,defaultValue = "") String cpu,
            @RequestParam(required = false,defaultValue = "") String camera,
            @RequestParam(required = false,defaultValue = "") Integer storage,
            @RequestParam(required = false,defaultValue = "") Integer brand) {
        try {
            Pageable pageable = PageRequest.of(page, 8);
            Page<Product> products = productService.findProductsByFilters(price, cpu, camera, storage, brand, pageable);
            if (products.isEmpty()) {
                return new ResponseEntity<>("No products found", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching products", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/brands")
    public ResponseEntity<?> showAllBrands() {
        try {
            List<Brand> brands = brandService.getAllBrands();
            if (brands.isEmpty()) {
                return new ResponseEntity<>("No products found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(brands, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching the brands.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}



