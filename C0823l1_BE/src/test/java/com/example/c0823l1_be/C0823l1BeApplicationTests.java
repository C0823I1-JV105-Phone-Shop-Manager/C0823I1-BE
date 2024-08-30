package com.example.c0823l1_be;

import com.example.c0823l1_be.dto.CustomerDTO;
import com.example.c0823l1_be.dto.CustomerViewDTO;
import com.example.c0823l1_be.dto.OrderDTO;
import com.example.c0823l1_be.entity.Customer;
import com.example.c0823l1_be.entity.Order;
import com.example.c0823l1_be.mapper.OrderMapper;
import com.example.c0823l1_be.repository.ICustomerRepository;
import com.example.c0823l1_be.repository.IOrderRepository;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class C0823l1BeApplicationTests {
    @Autowired
    IOrderRepository orderRepository;
    @Autowired
    ICustomerRepository customerRepository;

    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Test
    void contextLoads() {
    }
    @Test
    public void addOrder() {

        Order order = new Order();

        orderRepository.save(order);
        assertNotNull(orderRepository.findById(order.getId()));
    }

    @Test
    public void getAllCustomer() {
        Pageable pageable = PageRequest.of(0, 5);
        List<Customer> customerDTOS = customerRepository.findBy(Customer.class);
        System.out.println(customerDTOS);
        assertNotNull(!customerDTOS.isEmpty());
    }

    @Test
    public void getAllCustomerViewDTO() {
        Pageable pageable = PageRequest.of(0, 5);
        List<CustomerViewDTO> customerDTOS = customerRepository.findBy(CustomerViewDTO.class);
        System.out.println(customerDTOS);
        assertNotNull(!customerDTOS.isEmpty());
    }




}
