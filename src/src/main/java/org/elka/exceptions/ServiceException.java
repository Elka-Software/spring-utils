package org.elka.exceptions;

import org.elka.ResponseStatus;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -3303518302920463234L;

    private final ResponseStatus status;  

    public ServiceException(ResponseStatus status, String message) {
        super(message);
        this.status = status;
    }

    public ServiceException(ResponseStatus status) {
        this(status, status.message);
    }
}
