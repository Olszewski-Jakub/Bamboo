package live.olszewski.bamboo.apiKeys;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.http.ResponseEntity;


public interface ApiKeysService {

    ResponseEntity<ApiResponseDto<?>> addNewApiKey(String pandaId);

    ResponseEntity<ApiResponseDto<?>> deactivateApiKey(Long id);

    ResponseEntity<ApiResponseDto<?>> activateApiKey(Long id);

    ResponseEntity<ApiResponseDto<?>> getApiKeyByPanda(String pandaId);

    ResponseEntity<ApiResponseDto<?>> getApiKeyByOwner();

    ApiKeyDto verifyApiKey(String apiKey);

}
