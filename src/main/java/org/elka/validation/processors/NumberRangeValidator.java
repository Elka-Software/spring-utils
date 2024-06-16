package org.elka.validation.processors;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.elka.validation.markers.NumberRange;

public class NumberRangeValidator implements ConstraintValidator<NumberRange, Double> {
    private double max;
    private double min;

    @Override
    public void initialize(NumberRange constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && value >= min && value <= max;
    }
}
