package live.olszewski.bamboo.apiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * This class is used to build API responses.
 * It provides methods for building success, error, and unauthorized responses.
 */
public class ErrorBuilder {


    /**
     * Builds a 301 Moved Permanently response.
     *
     * @param message The message to include in the response.
     * @return A ResponseEntity with the status and message.
     */
    public ResponseEntity<ApiResponseDto<?>> code301(String message) {
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body(new ApiResponseDto<>(HttpStatus.MOVED_PERMANENTLY.value(), ApiStatus.ERROR, message, null));
    }

    /**
     * Builds a 302 Found response.
     *
     * @param message The message to include in the response.
     * @return A ResponseEntity with the status and message.
     */
    public ResponseEntity<ApiResponseDto<?>> code302(String message) {
        return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponseDto<>(HttpStatus.FOUND.value(), ApiStatus.ERROR, message, null));
    }

    /**
     * Builds a 303 See Other response.
     *
     * @param message The message to include in the response.
     * @return A ResponseEntity with the status and message.
     */
    public ResponseEntity<ApiResponseDto<?>> code303(String message) {
        return ResponseEntity.status(HttpStatus.SEE_OTHER).body(new ApiResponseDto<>(HttpStatus.SEE_OTHER.value(), ApiStatus.ERROR, message, null));
    }

    /**
     * Builds a 304 Not Modified response.
     *
     * @param message The message to include in the response.
     * @return A ResponseEntity with the status and message.
     */
    public ResponseEntity<ApiResponseDto<?>> code304(String message) {
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ApiResponseDto<>(HttpStatus.NOT_MODIFIED.value(), ApiStatus.ERROR, message, null));
    }

    /**
     * Builds a 307 Temporary Redirect response.
     *
     * @param message The message to include in the response.
     * @return A ResponseEntity with the status and message.
     */
    public ResponseEntity<ApiResponseDto<?>> code307(String message) {
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).body(new ApiResponseDto<>(HttpStatus.TEMPORARY_REDIRECT.value(), ApiStatus.ERROR, message, null));
    }

    /**
     * Builds a 400 Bad Request response.
     *
     * @param message The message to include in the response.
     * @return A ResponseEntity with the status and message.
     */
    public ResponseEntity<ApiResponseDto<?>> code400(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(), ApiStatus.ERROR, message, null));
    }

    /**
     * Builds a 401 Unauthorized response.
     *
     * @param message The message to include in the response.
     * @return A ResponseEntity with the status and message.
     */
    public ResponseEntity<ApiResponseDto<?>> code401(String message) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponseDto<>(HttpStatus.UNAUTHORIZED.value(), ApiStatus.ERROR, message, null));
    }

    /**
     * Builds a 403 Forbidden response.
     *
     * @param message The message to include in the response.
     * @return A ResponseEntity with the status and message.
     */
    public ResponseEntity<ApiResponseDto<?>> code403(String message) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponseDto<>(HttpStatus.FORBIDDEN.value(), ApiStatus.ERROR, message, null));
    }

    /**
     * Builds a 404 Not Found response.
     *
     * @param message The message to include in the response.
     * @return A ResponseEntity with the status and message.
     */
    public ResponseEntity<ApiResponseDto<?>> code404(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDto<>(HttpStatus.NOT_FOUND.value(), ApiStatus.ERROR, message, null));
    }

    /**
     * Builds a 405 Method Not Allowed response.
     *
     * @param message The message to include in the response.
     * @return A ResponseEntity with the status and message.
     */
    public ResponseEntity<ApiResponseDto<?>> code405(String message) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ApiResponseDto<>(HttpStatus.METHOD_NOT_ALLOWED.value(), ApiStatus.ERROR, message, null));
    }

    /**
     * Builds a 406 Not Acceptable response.
     *
     * @param message The message to include in the response.
     * @return A ResponseEntity with the status and message.
     */
    public ResponseEntity<ApiResponseDto<?>> code406(String message) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ApiResponseDto<>(HttpStatus.NOT_ACCEPTABLE.value(), ApiStatus.ERROR, message, null));
    }

    /**
     * Builds a 412 Precondition Failed response.
     *
     * @param message The message to include in the response.
     * @return A ResponseEntity with the status and message.
     */
    public ResponseEntity<ApiResponseDto<?>> code412(String message) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new ApiResponseDto<>(HttpStatus.PRECONDITION_FAILED.value(), ApiStatus.ERROR, message, null));
    }

    /**
     * Builds a 415 Unsupported Media Type response.
     *
     * @param message The message to include in the response.
     * @return A ResponseEntity with the status and message.
     */
    public ResponseEntity<ApiResponseDto<?>> code415(String message) {
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(new ApiResponseDto<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), ApiStatus.ERROR, message, null));
    }

    /**
     * Builds a 500 Internal Server Error response.
     *
     * @param message The message to include in the response.
     * @return A ResponseEntity with the status and message.
     */
    public ResponseEntity<ApiResponseDto<?>> code500(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ApiStatus.ERROR, message, null));
    }

    /**
     * Builds a 501 Not Implemented response.
     *
     * @param message The message to include in the response.
     * @return A ResponseEntity with the status and message.
     */
    public ResponseEntity<ApiResponseDto<?>> code501(String message) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ApiResponseDto<>(HttpStatus.NOT_IMPLEMENTED.value(), ApiStatus.ERROR, message, null));
    }

}
