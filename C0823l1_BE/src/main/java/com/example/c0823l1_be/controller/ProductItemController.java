package com.example.c0823l1_be.controller;

import com.example.c0823l1_be.dto.CustomerDTO;
import com.example.c0823l1_be.dto.ProductItemViewDTO;
import com.example.c0823l1_be.entity.Customer;
import com.example.c0823l1_be.service.ICustomerService;
import com.example.c0823l1_be.service.IProductItemService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@CrossOrigin(origins = "*")
public class ProductItemController {
    @Autowired
    IProductItemService productItemService;

    @GetMapping("/api/items")
    public ResponseEntity<?> findAll(@RequestParam(required = false, defaultValue = "", name = "name") String name)
{

    List<ProductItemViewDTO> productItemViewDTOS = productItemService.searchByProductName(name,ProductItemViewDTO.class);
    return new ResponseEntity<>(productItemViewDTOS, HttpStatus.OK);
}
    @GetMapping("/api/items/{id}")
    public ResponseEntity<?> findById(@PathVariable int id)
    {
        ProductItemViewDTO productItemView = productItemService.findById(id,ProductItemViewDTO.class);
        return new ResponseEntity<>(productItemView, HttpStatus.OK);
    }
}
