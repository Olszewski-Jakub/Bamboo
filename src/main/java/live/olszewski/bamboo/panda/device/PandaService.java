package live.olszewski.bamboo.panda.device;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.http.ResponseEntity;


public interface PandaService {

    ResponseEntity<ApiResponseDto<?>> getAllPandaDevices();

    ResponseEntity<ApiResponseDto<?>> getPandaDevicesByOwner();

    Boolean updatePandaApiKey(Long pandaId, String apiKey);

}
