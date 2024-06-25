package org.elka.spring.validation.processors;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.elka.spring.validation.markers.PosNum;

public class PositiveNumberValidator implements ConstraintValidator<PosNum, Number> {
    @Override
    public boolean isValid(Number value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && value.doubleValue() > 0;
    }
}
