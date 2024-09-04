package com.example.c0823l1_be.CustomValidator.annotation;

import com.example.c0823l1_be.CustomValidator.custion_validator.UniqueValidatorPhone;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueValidatorPhone.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueConstraintPhone {
    String message() default "Số điện thoại đã dùng cho nhà cung cấp khác";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};

}
