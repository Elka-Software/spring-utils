package org.elka;

import jakarta.annotation.Nullable;
import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;

import static java.util.Objects.requireNonNull;

@Builder
public record Response<T> (String message, String status, @Nullable T data) implements Serializable {
    @Serial
    private static final long serialVersionUID = -1133637474601003587L;

    public Response {
        requireNonNull(message);
        requireNonNull(status);
    }
}
