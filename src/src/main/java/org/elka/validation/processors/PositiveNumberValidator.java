package org.elka.validation.processors;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.elka.validation.markers.PosNum;

public class PositiveNumberValidator implements ConstraintValidator<PosNum, Number> {
    @Override
    public boolean isValid(Number value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && value.doubleValue() > 0;
    }
}
