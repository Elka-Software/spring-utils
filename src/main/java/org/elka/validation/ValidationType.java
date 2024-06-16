package org.elka.validation;

import jakarta.validation.*;

import java.util.Set;

public interface ValidationType {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    <T> Set<?> validate(T objectToValidate);

    default <T> Set<ConstraintViolation<T>> use(T object) {
        return factory.getValidator().validate(object);
    }
}
