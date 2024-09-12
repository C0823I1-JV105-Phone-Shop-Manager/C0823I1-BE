package com.example.c0823l1_be.service.imp;

import com.example.c0823l1_be.entity.Customer;
import com.example.c0823l1_be.repository.ICustomerRepository;
import com.example.c0823l1_be.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository customerRepository;


    @Override
    public <T> List<T> findAll(Class<T> classType) {
        return customerRepository.findBy(classType);

    }

    @Override
    public <T> T findById(int id, Class<T> classType) {
        return customerRepository.findById(id,classType);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public <T> List<T> searchByCustomerName(String name,  Class<T> classType) {
        return customerRepository.findByNameContainingIgnoreCase(name, classType);
    }
}
