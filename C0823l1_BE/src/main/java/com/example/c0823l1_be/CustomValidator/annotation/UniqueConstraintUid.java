package com.example.c0823l1_be.CustomValidator.annotation;

import com.example.c0823l1_be.CustomValidator.custion_validator.UniqueValidatorUid;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = UniqueValidatorUid.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueConstraintUid {
    String message() default "Mã số đã tồn tại, không thể dùng";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
