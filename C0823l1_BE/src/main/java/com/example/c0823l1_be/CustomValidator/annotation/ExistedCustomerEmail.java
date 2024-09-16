package com.example.c0823l1_be.CustomValidator.annotation;

import com.example.c0823l1_be.CustomValidator.custion_validator.ExistedCustomerEmailValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy= ExistedCustomerEmailValidator.class)
public @interface ExistedCustomerEmail {
    String message() default "Email khách hàng đã tồn tại trong hệ thống";
    Class[] groups() default {};
    Class[] payload() default {};
}
