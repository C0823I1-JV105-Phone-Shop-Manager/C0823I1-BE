package com.example.c0823l1_be.service.imp;

import com.example.c0823l1_be.entity.ProductItem;
import com.example.c0823l1_be.repository.IProductItemRepository;
import com.example.c0823l1_be.service.IProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductItemService implements IProductItemService {
    @Autowired
    private IProductItemRepository productItemRepository;
    @Override
    public <T> List<T> findAll(Class<T> classType) {
        return productItemRepository.findBy(classType);
    }

    @Override
    public <T> T findById(int id, Class<T> classType) {
        return productItemRepository.findById(id,classType);
    }

    @Override
    public void save(ProductItem productItem) {
        productItemRepository.save(productItem);
    }

    @Override
    public void deleteById(int id) {
        productItemRepository.deleteById(id);
    }

    @Override
    public <T> T searchByProductName(String name, Class<T> classType) {
        return productItemRepository.findByProduct_NameContainingIgnoreCase(name,classType);
    }
}
