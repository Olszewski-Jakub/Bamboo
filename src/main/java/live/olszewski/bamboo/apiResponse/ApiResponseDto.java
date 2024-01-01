package live.olszewski.bamboo.apiResponse;

import java.text.SimpleDateFormat;

/**
 * This class represents a data transfer object (DTO) for API responses.
 *
 * @param <T> The type of the data included in the response.
 */
public class ApiResponseDto<T> {
    private String timestamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new java.util.Date());
    ;
    private int statusCode;
    private ApiStatus status;
    private String message;
    private T data;

    /**
     * Constructs a new ApiResponseDto with no initial values.
     */
    public ApiResponseDto() {

    }

    /**
     * Constructs a new ApiResponseDto with the provided status code, status, message, and data.
     *
     * @param statusCode The status code of the response.
     * @param status     The status of the response.
     * @param message    The message of the response.
     * @param data       The data included in the response.
     */
    public ApiResponseDto(int statusCode, ApiStatus status, String message, T data) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * Constructs a new ApiResponseDto with the provided status code, status, message, and data.
     *
     * @param timestamp  The timestamp of the response.
     * @param statusCode The status code of the response.
     * @param status     The status of the response.
     * @param message    The message of the response.
     * @param data       The data included in the response.
     */
    public ApiResponseDto(String timestamp, int statusCode, ApiStatus status, String message, T data) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.data = data;
    }


    /**
     * Retrieves the status code of the response.
     *
     * @return The status code of the response.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the status code of the response.
     *
     * @param statusCode The status code to set.
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Retrieves the status of the response.
     *
     * @return The status of the response.
     */
    public ApiStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the response.
     *
     * @param status The status to set.
     */
    public void setStatus(ApiStatus status) {
        this.status = status;
    }

    /**
     * Retrieves the message of the response.
     *
     * @return The message of the response.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of the response.
     *
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retrieves the data of the response.
     *
     * @return The data of the response.
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data of the response.
     *
     * @param data The data to set.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Retrieves the timestamp of the response.
     *
     * @return The timestamp of the response.
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the response.
     *
     * @param timestamp The timestamp to set.
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
