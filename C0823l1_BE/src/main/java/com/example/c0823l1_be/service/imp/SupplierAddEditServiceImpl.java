package com.example.c0823l1_be.service.imp;

import com.example.c0823l1_be.entity.Supplier;
import com.example.c0823l1_be.repository.ISupplierAddEditRepository;
import com.example.c0823l1_be.service.ISupplierAddEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierAddEditServiceImpl implements ISupplierAddEditService {
    @Autowired
    private ISupplierAddEditRepository iSupplierAddEditRepository;

    @Override
    public Boolean saveSupplier(Supplier supplier) {
        try {
            iSupplierAddEditRepository.save(supplier);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
