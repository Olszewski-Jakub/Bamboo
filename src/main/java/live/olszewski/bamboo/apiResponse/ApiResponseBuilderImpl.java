package live.olszewski.bamboo.apiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class ApiResponseBuilderImpl implements ApiResponseBuilder {

    public <T> ApiResponseDto<T> buildSuccessResponse(int statusCode, String message, T data) {
        return new ApiResponseDto<>(statusCode, ApiStatus.SUCCESS, message, data);
    }

    public ApiResponseDto<Void> buildErrorResponse(int statusCode, String message) {
        return new ApiResponseDto<>(statusCode, ApiStatus.ERROR, message, null);
    }


    @Override
    public ApiResponseDto<Void> buildUnauthorizedResponse(String message) {
        return new ApiResponseDto<>(HttpStatus.UNAUTHORIZED.value(), ApiStatus.ERROR, message, null);
    }

    @Override
    public ErrorBuilder error() {
        return new ErrorBuilder();
    }

    @Override
    public SuccessBuilder success() {
        return new SuccessBuilder();
    }
}



