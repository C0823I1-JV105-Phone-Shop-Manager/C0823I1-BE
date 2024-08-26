package com.example.c0823l1_be;

import com.example.c0823l1_be.entity.Order;
import com.example.c0823l1_be.repository.IOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class C0823l1BeApplicationTests {
    @Autowired
    IOrderRepository orderRepository;
    @Test
    void contextLoads() {
    }
    @Test
    public void addOrder() {

        Order order = new Order();


        orderRepository.save(order);
        assertNotNull(orderRepository.findById(order.getId()));
    }

}
