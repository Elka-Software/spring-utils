package org.elka.validation.markers;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.elka.validation.processors.NumberRangeValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NumberRangeValidator.class)
public @interface NumberRange {
    String message() default "Value must be within the specified range";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    double min();
    double max();
}
