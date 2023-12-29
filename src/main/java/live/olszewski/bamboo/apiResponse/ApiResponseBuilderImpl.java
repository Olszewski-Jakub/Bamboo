package live.olszewski.bamboo.apiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * This class implements the ApiResponseBuilder interface and provides methods for building API responses.
 */
@Service
public class ApiResponseBuilderImpl implements ApiResponseBuilder {

    /**
     * Builds a successful API response with the provided status code, message, and data.
     *
     * @param <T>        The type of the data included in the response.
     * @param statusCode The status code of the response.
     * @param message    The message of the response.
     * @param data       The data included in the response.
     * @return An ApiResponseDto object representing the successful API response.
     */
    public <T> ApiResponseDto<T> buildSuccessResponse(int statusCode, String message, T data) {
        return new ApiResponseDto<>(statusCode, ApiStatus.SUCCESS, message, data);
    }

    /**
     * Builds an error API response with the provided status code and message.
     *
     * @param statusCode The status code of the response.
     * @param message The message of the response.
     * @return An ApiResponseDto object representing the error API response.
     */
    public ApiResponseDto<Void> buildErrorResponse(int statusCode, String message) {
        return new ApiResponseDto<>(statusCode, ApiStatus.ERROR, message, null);
    }

    /**
     * Builds an unauthorized API response with the provided message.
     *
     * @param message The message of the response.
     * @return An ApiResponseDto object representing the unauthorized API response.
     */
    @Override
    public ApiResponseDto<Void> buildUnauthorizedResponse(String message) {
        return new ApiResponseDto<>(HttpStatus.UNAUTHORIZED.value(), ApiStatus.ERROR, message, null);
    }
}



