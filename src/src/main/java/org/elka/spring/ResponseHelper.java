package org.elka.spring;

import org.elka.Response;
import org.elka.ResponseStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ResponseHelper {

    /**
     * Method to encapsulate a successful response
     * @param data response data
     * @return Response
     * @param <T> type of response data
     */
    @Contract("_ -> new")
    public static <T> @NotNull Response<T> success(T data) {
        return new Response<>(
                ResponseStatus.SUCCESS.message,
                ResponseStatus.SUCCESS.toString(),
                data);
    }

    /**
     * Method to encapsulate an error response
     * @param statusEnum error response status
     * @return Response
     * @param <T>
     */
    @Contract("_ -> new")
    public static <T> @NotNull Response<T> error(ResponseStatus statusEnum) {
        return error(statusEnum, statusEnum.message);
    }

    /**
     * Method to encapsulate an error response with a custom error message
     * @param statusEnum error response status
     * @param errorMsg custom error message
     * @return Response
     * @param <T>
     */
    @Contract("_, _ -> new")
    public static <T> @NotNull Response<T> error(@NotNull ResponseStatus statusEnum, String errorMsg) {
        return new Response<>(errorMsg, statusEnum.toString(), null);
    }
}
