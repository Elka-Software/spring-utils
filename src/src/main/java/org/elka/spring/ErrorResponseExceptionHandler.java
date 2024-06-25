package org.elka.spring;

import org.elka.ErrorResponse;
import org.elka.exceptions.ServiceException;
import org.elka.exceptions.WebClientDownException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ErrorResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WebClientDownException.class)
    public ResponseEntity<String> handleWebClientDownException(@NotNull WebClientDownException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(
                    "Web Client is down: " + ex.getMessage(),
                    ex.getCause(),
                    HttpStatus.SERVICE_UNAVAILABLE).toString(),
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> handleServiceException(@NotNull ServiceException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(
                    "Service exception occurred: " + ex.getMessage(),
                    ex.getCause(),
                    HttpStatus.INTERNAL_SERVER_ERROR).toString(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(@NotNull Exception ex) {
        return new ResponseEntity<>(
                new ErrorResponse(
                    "An error occurred: " + ex.getMessage(),
                    ex.getCause(),
                    HttpStatus.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
