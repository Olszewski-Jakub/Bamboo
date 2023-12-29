package live.olszewski.bamboo.apiKeys;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.panda.PandaDao;
import org.springframework.http.ResponseEntity;


public interface ApiKeysService {

    ResponseEntity<ApiResponseDto<?>> addNewApiKey(String pandaId);

    ResponseEntity<ApiResponseDto<?>> deactivateApiKey(Long id);

    ResponseEntity<ApiResponseDto<?>> activateApiKey(Long id);

    ResponseEntity<ApiResponseDto<?>> getApiKeyByPanda(String pandaId);

    ResponseEntity<ApiResponseDto<?>> getApiKeyByOwner();

    ApiKeyDto verifyApiKey(String apiKey);

    PandaDao getPandaByApiKey(String apiKey);

}
