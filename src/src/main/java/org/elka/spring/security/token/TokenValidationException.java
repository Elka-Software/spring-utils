package org.elka.spring.security.token;

import org.elka.exceptions.ServiceException;

public class TokenValidationException extends ServiceException {
    public TokenValidationException() {
        super();
    }

    public TokenValidationException(String message) {
        super(message);
    }

    public TokenValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
