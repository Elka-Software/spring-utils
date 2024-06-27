package org.elka.spring;

import org.elka.Response;
import org.elka.ResponseStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityHelper {

    /**
     * Method to encapsulate a successful response
     * @param data response data
     * @return ResponseEntity with response
     * @param <T> type of response data
     */
    @Contract("_ -> new")
    public static <T> @NotNull ResponseEntity<Response<T>> success(T data) {
        Response<T> response = new Response<>(
                ResponseStatus.SUCCESS.message,
                ResponseStatus.SUCCESS.toString(),
                data
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Method to encapsulate an error response
     * @param statusEnum error response status
     * @return ResponseEntity with response
     * @param <T>
     */
    @Contract("_ -> new")
    public static <T> @NotNull ResponseEntity<Response<T>> error(ResponseStatus statusEnum) {
        return error(statusEnum, statusEnum.message);
    }

    /**
     * Method to encapsulate an error response with a custom error message
     * @param statusEnum error response status
     * @param errorMsg custom error message
     * @return ResponseEntity with response
     * @param <T>
     */
    @Contract("_, _ -> new")
    public static <T> @NotNull ResponseEntity<Response<T>> error(@NotNull ResponseStatus statusEnum, String errorMsg) {
        Response<T> response = new Response<>(
                errorMsg,
                statusEnum.toString(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.valueOf(statusEnum.code));
    }
}
