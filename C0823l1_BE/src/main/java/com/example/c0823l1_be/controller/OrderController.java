package com.example.c0823l1_be.controller;

import com.example.c0823l1_be.CustomValidator.annotation.ExistedCustomerEmail;
import com.example.c0823l1_be.dto.OrderDTO;
import com.example.c0823l1_be.dto.OrderViewDTO;
import com.example.c0823l1_be.dto.StaffViewDTO;
import com.example.c0823l1_be.entity.*;
import com.example.c0823l1_be.exception.ExistedEmailException;
import com.example.c0823l1_be.repository.IOrderRepository;
import com.example.c0823l1_be.repository.StaffRepository;
import com.example.c0823l1_be.service.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    IStaffService staffService;
    @Autowired
    IProductStatusService productStatusService;
    @GetMapping("/api/orders")
    public ResponseEntity<?> findAll()
    {
        List<OrderViewDTO> orderList = orderService.findBy(OrderViewDTO.class);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }
    @Transactional
    // test order
    @PostMapping("/api/orders")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println(username);
        List <ProductItem> productItemList = Arrays.stream(orderDTO.getProductItemList()).map(id -> productItemService.findById(id, ProductItem.class)).collect(Collectors.toList());
        Staff staff = staffService.findByUserName(username,Staff.class);
        Order targetOrder = new Order();
        targetOrder.setStaff(staff);
        if (orderDTO.getCustomer().getId() != null) {
            Customer existCustomer = (Customer) customerService.findById(orderDTO.getCustomer().getId(), Customer.class);
            BeanUtils.copyProperties(orderDTO.getCustomer(), existCustomer);
            targetOrder.setCustomer(existCustomer);
        } else {
            if (customerService.findByEmail(orderDTO.getCustomer().getEmail()) !=null) throw new ExistedEmailException("Địa chỉ email đã tồn tại !");
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


    @GetMapping("/api/orders/{id}")
    public ResponseEntity<?> findById(@PathVariable String id)
    {
        Order order = orderService.findById(id,Order.class);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


}
