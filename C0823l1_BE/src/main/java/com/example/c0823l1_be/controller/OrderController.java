package com.example.c0823l1_be.controller;

import com.example.c0823l1_be.dto.OrderDTO;
import com.example.c0823l1_be.dto.OrderViewDTO;
import com.example.c0823l1_be.entity.Customer;
import com.example.c0823l1_be.entity.Order;
import com.example.c0823l1_be.entity.ProductItem;
import com.example.c0823l1_be.entity.Staff;
import com.example.c0823l1_be.repository.IOrderRepository;
import com.example.c0823l1_be.repository.StaffRepository;
import com.example.c0823l1_be.service.ICustomerService;
import com.example.c0823l1_be.service.IOrderService;
import com.example.c0823l1_be.service.IProductItemService;
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
        Customer customer = (Customer) customerService.findById(orderDTO.getCustomer().getId(), Customer.class);
        targetOrder.setCustomer(customer);
        targetOrder.setStaff(staff);
        targetOrder.setProductItemList(productItemList);
        Order saveOrder = orderService.save(targetOrder);
        for (ProductItem productItem : productItemList) {
           productItem.setOrder(saveOrder);
           productItemService.save(productItem);
        }
        System.out.println(targetOrder.getId());
        return ResponseEntity.ok("OK");
    }


}
