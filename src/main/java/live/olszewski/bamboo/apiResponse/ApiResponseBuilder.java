package live.olszewski.bamboo.apiResponse;

public interface ApiResponseBuilder {

    <T> ApiResponseDto<T> buildSuccessResponse(int statusCode, String message, T data);
    ApiResponseDto<Void> buildErrorResponse(int statusCode, String message);

    ApiResponseDto<Void> buildUnauthorizedResponse(String message);
}
