package com.example.c0823l1_be.controller;

import com.example.c0823l1_be.dto.OrderDTO;
import com.example.c0823l1_be.dto.OrderViewDTO;
import com.example.c0823l1_be.entity.*;
import com.example.c0823l1_be.repository.IOrderRepository;
import com.example.c0823l1_be.repository.StaffRepository;
import com.example.c0823l1_be.service.ICustomerService;
import com.example.c0823l1_be.service.IOrderService;
import com.example.c0823l1_be.service.IProductItemService;
import com.example.c0823l1_be.service.IProductStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")

public class OrderController {

    @Autowired
    ICustomerService customerService;
    @Autowired
    IProductItemService productItemService;
    @Autowired
    IOrderService orderService;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    IProductStatusService productStatusService;
    @GetMapping("/api/orders")
    public ResponseEntity<?> findAll()
    {
        List<OrderViewDTO> orderList = orderService.findBy(OrderViewDTO.class);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }
    // test order
    @PostMapping("/api/orders")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        List <ProductItem> productItemList = Arrays.stream(orderDTO.getProductItemList()).map(id -> productItemService.findById(id, ProductItem.class)).collect(Collectors.toList());
        Order targetOrder = new Order();
        Staff staff = staffRepository.findById(orderDTO.getStaff().getId()).get();
        targetOrder.setStaff(staff);

        if (orderDTO.getCustomer().getId() != null) {
            Customer existCustomer = (Customer) customerService.findById(orderDTO.getCustomer().getId(), Customer.class);
            BeanUtils.copyProperties(orderDTO.getCustomer(), existCustomer);
            targetOrder.setCustomer(existCustomer);
        } else {
            Customer newCustomer = new Customer();
            BeanUtils.copyProperties(orderDTO.getCustomer(), newCustomer);
            Customer createdCustomer = customerService.save(newCustomer);
            targetOrder.setCustomer(createdCustomer);
        }
        targetOrder.setProductItemList(productItemList);
        Order saveOrder = orderService.save(targetOrder);
        for (ProductItem productItem : productItemList) {
           productItem.setOrder(saveOrder);
           productItem.setProductStatus(productStatusService.findById(2).get());
           productItemService.save(productItem);
        }
        System.out.println(targetOrder.getId());
        return ResponseEntity.ok(orderService.findById(targetOrder.getId(), Order.class));
    }


}
