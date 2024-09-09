package com.example.c0823l1_be.CustomValidator.annotation;

import com.example.c0823l1_be.CustomValidator.custion_validator.UniqueValidatorEmail;
import com.example.c0823l1_be.CustomValidator.custion_validator.UniqueValidatorPhone;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueValidatorEmail.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueConstraintEmail {
    String message() default "Vui lòng dùng email khác, email này không được phép sử dụng";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
