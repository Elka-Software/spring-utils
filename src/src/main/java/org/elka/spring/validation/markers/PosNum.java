package org.elka.spring.validation.markers;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.elka.spring.validation.processors.PositiveNumberValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PositiveNumberValidator.class)
public @interface PosNum {
    String message() default "Value must be a positive number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
