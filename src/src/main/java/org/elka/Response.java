package org.elka;

import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;

import static java.util.Objects.requireNonNull;

public record Response<T> (String message, HttpStatus status, @Nullable T data) {
    public Response {
        requireNonNull(message);
        requireNonNull(status);
    }
}
