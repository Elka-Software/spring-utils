package org.elka;

public enum ResponseStatus {
    SUCCESS(200, "Request processed successfully"),
    CREATED(201, "Resource was created successfully"),
    NO_CONTENT(204, "No content found in the location"),
    UNAUTHORIZED(401, "User authentication failed"),
    FORBIDDEN(403, "Insufficient permissions"),
    NOT_FOUND(404, "Could not locate resource"),
    SERVICE_ERROR(500, "Server is on a trip, please try again later"),
    PARAM_INVALID(1000, "Invalid parameter");

    public final Integer code;
    public final String message;

    ResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
