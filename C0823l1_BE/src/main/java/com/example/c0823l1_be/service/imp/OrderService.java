package com.example.c0823l1_be.service.imp;

import com.example.c0823l1_be.entity.Order;
import com.example.c0823l1_be.repository.IOrderRepository;
import com.example.c0823l1_be.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;


    @Override
    public <T> List<T> findAll(Class<T> classType) {
        return orderRepository.findBy(classType);
    }

    @Override
    public <T> T findById(String id, Class<T> classType) {
        return orderRepository.findById(id,classType);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }
}
