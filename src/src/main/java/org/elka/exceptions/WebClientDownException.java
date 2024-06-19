package org.elka.exceptions;

public class WebClientDownException extends RuntimeException {
    public WebClientDownException() {
        super();
    }

    public WebClientDownException(String message) {
        super(message);
    }

    public WebClientDownException(String message, Throwable cause) {
        super(message, cause);
    }
}
