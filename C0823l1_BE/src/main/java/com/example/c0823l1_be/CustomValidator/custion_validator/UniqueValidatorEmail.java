package com.example.c0823l1_be.CustomValidator.custion_validator;

import com.example.c0823l1_be.CustomValidator.annotation.UniqueConstraintEmail;
import com.example.c0823l1_be.repository.ISupplierAddEditRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueValidatorEmail implements ConstraintValidator<UniqueConstraintEmail,String> {
    @Autowired
    ISupplierAddEditRepository iSupplierAddEditRepository;

    @Override
    public void initialize(UniqueConstraintEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Integer countUid   = iSupplierAddEditRepository.countAllByEmail(value);
        return countUid == 0L;
    }
}
