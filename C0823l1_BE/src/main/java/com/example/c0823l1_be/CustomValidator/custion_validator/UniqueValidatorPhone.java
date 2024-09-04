package com.example.c0823l1_be.CustomValidator.custion_validator;

import com.example.c0823l1_be.CustomValidator.annotation.UniqueConstraintPhone;
import com.example.c0823l1_be.repository.ISupplierAddEditRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueValidatorPhone implements ConstraintValidator<UniqueConstraintPhone,String> {
    @Autowired
    ISupplierAddEditRepository iSupplierAddEditRepository;

    @Override
    public void initialize(UniqueConstraintPhone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Integer countUid   = iSupplierAddEditRepository.countAllByPhone(value);
        return countUid == 0L;
    }
}
