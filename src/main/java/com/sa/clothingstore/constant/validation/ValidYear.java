package com.sa.clothingstore.constant.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = ValidYearValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface ValidYear {
    String message() default "Year must not be null or greater than current year";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

