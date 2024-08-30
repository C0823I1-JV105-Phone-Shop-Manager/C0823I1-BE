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
        Long numOfUid = 0L;
        try {
            if (supplier.getUid() == null) {
                return false;
            }
            if (iSupplierAddEditRepository.countAllByUid(supplier.getUid()) > 0
                    || iSupplierAddEditRepository.countAllByPhone(supplier.getPhone()) > 0
                    || iSupplierAddEditRepository.countAllByEmail(supplier.getEmail()) > 0) {
                return false;
            }
            iSupplierAddEditRepository.save(supplier);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public Boolean updateSupplier(Supplier supplier) {
        try {
            String currentlyUid = supplier.getUid();
            Supplier dbSupplier = iSupplierAddEditRepository.findSupplierByUid(currentlyUid);
            Integer curId = supplier.getId();
            Integer dbId = dbSupplier.getId();
            String dbUid = dbSupplier.getUid();
            if (curId == null || curId == 0 || dbId != curId || !dbUid.equals(currentlyUid)) {
                return false;
            } else if (iSupplierAddEditRepository.countAllByIdNotAndUid(curId, currentlyUid) > 0
                    || iSupplierAddEditRepository.countAllByIdNotAndPhone(curId, supplier.getPhone()) > 0
                    || iSupplierAddEditRepository.countAllByIdNotAndEmail(curId, supplier.getEmail()) > 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
