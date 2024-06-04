package com.sa.clothingstore.constant.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FutureOrTodayValidator.class)
@Documented
public @interface  FutureOfDateFormat {
    String message() default "Date must be in the future or today";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
