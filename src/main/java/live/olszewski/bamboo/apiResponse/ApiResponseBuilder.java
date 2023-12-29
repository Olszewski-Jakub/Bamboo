package live.olszewski.bamboo.apiResponse;

/**
 * This interface defines methods for building API responses.
 */
public interface ApiResponseBuilder {

    /**
     * Builds a successful API response with the provided status code, message, and data.
     *
     * @param <T>        The type of the data included in the response.
     * @param statusCode The status code of the response.
     * @param message    The message of the response.
     * @param data       The data included in the response.
     * @return An ApiResponseDto object representing the successful API response.
     */
    <T> ApiResponseDto<T> buildSuccessResponse(int statusCode, String message, T data);

    /**
     * Builds an error API response with the provided status code and message.
     *
     * @param statusCode The status code of the response.
     * @param message    The message of the response.
     * @return An ApiResponseDto object representing the error API response.
     */
    ApiResponseDto<Void> buildErrorResponse(int statusCode, String message);

    /**
     * Builds an unauthorized API response with the provided message.
     *
     * @param message The message of the response.
     * @return An ApiResponseDto object representing the unauthorized API response.
     */
    ApiResponseDto<Void> buildUnauthorizedResponse(String message);
}