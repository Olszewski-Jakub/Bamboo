package live.olszewski.bamboo.apiResponse;

import org.springframework.http.ResponseEntity;

/**
 * This class is used to build successful API responses.
 * It provides methods for building 200 OK, 201 Created, 202 Accepted, and 204 No Content responses.
 */
public class SuccessBuilder {

    /**
     * Builds a 200 OK response.
     *
     * @param message The message to include in the response.
     * @param data    The data to include in the response.
     * @return A ResponseEntity with the status, message, and data.
     */
    public <T> ResponseEntity<ApiResponseDto<T>> code200(String message, T data) {
        return ResponseEntity.ok(new ApiResponseDto<>(200, ApiStatus.SUCCESS, message, data));
    }

    /**
     * Builds a 201 Created response.
     *
     * @param message The message to include in the response.
     * @param data    The data to include in the response.
     * @return A ResponseEntity with the status, message, and data.
     */
    public <T> ResponseEntity<ApiResponseDto<T>> code201(String message, T data) {
        return ResponseEntity.status(201).body(new ApiResponseDto<>(201, ApiStatus.SUCCESS, message, data));
    }

    /**
     * Builds a 202 Accepted response.
     *
     * @param message The message to include in the response.
     * @param data    The data to include in the response.
     * @return A ResponseEntity with the status, message, and data.
     */
    public <T> ResponseEntity<ApiResponseDto<T>> code202(String message, T data) {
        return ResponseEntity.status(202).body(new ApiResponseDto<>(202, ApiStatus.SUCCESS, message, data));
    }

    /**
     * Builds a 204 No Content response.
     *
     * @param message The message to include in the response.
     * @param data    The data to include in the response.
     * @return A ResponseEntity with the status, message, and data.
     */
    public <T> ResponseEntity<ApiResponseDto<T>> code204(String message, T data) {
        return ResponseEntity.status(204).body(new ApiResponseDto<>(204, ApiStatus.SUCCESS, message, data));
    }
}