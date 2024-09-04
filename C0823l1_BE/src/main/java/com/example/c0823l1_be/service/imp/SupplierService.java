package com.example.c0823l1_be.service.imp;

import com.example.c0823l1_be.entity.Supplier;
import com.example.c0823l1_be.repository.ISupplierRepository;
import com.example.c0823l1_be.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService implements ISupplierService {
    @Autowired
    private ISupplierRepository iSupplierRepository;

    @Override
    public Page<Supplier> findAll(Pageable pageable) {
        return iSupplierRepository.findAll(pageable);
    }

    @Override
    public Page<Supplier> searchSuppliers(String search, Pageable pageable) {
        return iSupplierRepository.searchSuppliers(search, pageable);
    }


    public void deleteByIds(List<Integer> ids) {
        iSupplierRepository.deleteAllById(ids);
    }

    @Override
    public Page<Supplier> searchByName(String name, Pageable pageable) {
        return iSupplierRepository.findByName(name, pageable);
    }

    @Override
    public List<String> findAllSupplierNames() {
        return iSupplierRepository.findAll()
                .stream()
                .map(Supplier::getName)
                .collect(Collectors.toList());
    }

}
