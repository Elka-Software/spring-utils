package org.elka.validation;

import jakarta.validation.ConstraintViolation;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleValidationImpl implements ValidationType {
    @Override
    public <T> Set<?> validate(T objectToValidate) {
        var violations = this.use(objectToValidate);
        if (!violations.isEmpty())
            return violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());

        return Collections.emptySet();
    }
}
