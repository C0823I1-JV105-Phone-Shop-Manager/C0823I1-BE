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
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IBrandService brandService;

    // Hiển thị tất cả sản phẩm
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

    // Lọc sản phẩm theo các bộ lọc
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
                return new ResponseEntity<>("Không tìm thấy sản phẩm ", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching products", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Tìm kiếm sản phẩm bằng từ khóa và bộ lọc
    @GetMapping("/search/search?page={page}||price={price}||cpu={cpu}||camera={camera}||storage={storage}||brand={brand}&&keyword={keyword}")
    public ResponseEntity<?> searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer price,
            @RequestParam(required = false) String cpu,
            @RequestParam(required = false) String camera,
            @RequestParam(required = false) Integer storage,
            @RequestParam(required = false) Integer brand,
            Pageable pageable) {
        try {
            Page<Product> products = productService.findProductsByFiltersAndKeyword(price, cpu, camera, storage, brand, keyword, pageable);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found for the given filters and keyword.");
            }

            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while searching products", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Xóa sản phẩm theo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Optional<?> product = productService.findProductById(id);

        if (product.isPresent()) {
            productService.deleteProductById(id);
            return ResponseEntity.ok().body("Product deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    // Lấy sản phẩm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.findProductById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
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
