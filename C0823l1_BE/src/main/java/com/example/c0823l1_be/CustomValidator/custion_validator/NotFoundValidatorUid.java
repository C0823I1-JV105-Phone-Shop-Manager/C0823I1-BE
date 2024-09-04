package com.example.c0823l1_be.CustomValidator.custion_validator;

import com.example.c0823l1_be.CustomValidator.annotation.NotFoundConstraintUid;
import com.example.c0823l1_be.entity.Supplier;
import com.example.c0823l1_be.repository.ISupplierAddEditRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotFoundValidatorUid implements ConstraintValidator<NotFoundConstraintUid,String> {
    @Autowired
    ISupplierAddEditRepository iSupplierAddEditRepository;

    private String idField;
    @Override
    public void initialize(NotFoundConstraintUid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String uid,  ConstraintValidatorContext context) {
        if (uid == null ){
            return false;
        }
        Supplier supplierByUid = iSupplierAddEditRepository.findSupplierByUid(uid);
        if (supplierByUid == null){
            return false;
        }
        return true;
    }
}
