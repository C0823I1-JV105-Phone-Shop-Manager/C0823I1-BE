package com.example.c0823l1_be.CustomValidator.custion_validator;


import com.example.c0823l1_be.CustomValidator.annotation.ExistedCustomerEmail;
import com.example.c0823l1_be.service.ICustomerService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistedCustomerEmailValidator implements ConstraintValidator<ExistedCustomerEmail, String> {
    @Autowired
    private ICustomerService customerService;

    @Override
    public void initialize(ExistedCustomerEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return customerService.findByEmail(email) == null;
    }
}
