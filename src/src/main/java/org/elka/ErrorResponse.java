package org.elka;

import org.springframework.http.HttpStatus;

import static java.util.Objects.requireNonNull;

public record ErrorResponse(String message, Throwable cause, HttpStatus httpStatus) {
    public ErrorResponse {
        requireNonNull(message);
        requireNonNull(httpStatus);
    }
}
