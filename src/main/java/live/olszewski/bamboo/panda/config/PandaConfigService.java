package live.olszewski.bamboo.panda.config;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.http.ResponseEntity;

public interface PandaConfigService {


    ResponseEntity<byte[]> downloadPandaConfig(Long pandaId);

    ResponseEntity<ApiResponseDto<?>> verifyPandaConfig(String pandaConfigJson);

}
