package live.olszewski.bamboo.apiResponse;

import org.springframework.stereotype.Service;

@Service
public class ApiResponseBuilderImpl implements ApiResponseBuilder {

    public <T> ApiResponseDto<T> buildSuccessResponse(int statusCode, String message, T data) {
        return new ApiResponseDto<>(statusCode, ApiStatus.SUCCESS, message, data);
    }

    public ApiResponseDto<Void> buildErrorResponse(int statusCode, String message) {
        return new ApiResponseDto<>(statusCode, ApiStatus.ERROR, message, null);
    }
}



