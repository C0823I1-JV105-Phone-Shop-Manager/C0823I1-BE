package com.example.c0823l1_be.service.imp;

import com.example.c0823l1_be.entity.Supplier;
import com.example.c0823l1_be.repository.ISupplierRepository;
import com.example.c0823l1_be.service.ISupplierService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;


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

    @Override
    @Transactional
    public void deleteByUids(List<String> uids) {
        try {
            iSupplierRepository.deleteAllByUid(uids);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Page<Supplier> searchByName(String name, Pageable pageable) {
        return iSupplierRepository.findByName(name, pageable);
    }

    @Override
    public Page<Supplier> searchByAddressAndName(String address, String search, Pageable pageable) {
        return iSupplierRepository.findByAddressContainingAndNameOrPhone(address, search, pageable);
    }
}