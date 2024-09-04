package com.example.c0823l1_be.CustomValidator.annotation;


import com.example.c0823l1_be.CustomValidator.custion_validator.NotFoundValidatorUid;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotFoundValidatorUid.class)
public @interface NotFoundConstraintUid {
    String message() default "Không tìm thấy mã số nhà cung cấp";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};

    String idField() default "id"; // Add the id field
}
