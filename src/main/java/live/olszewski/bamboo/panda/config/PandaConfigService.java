package live.olszewski.bamboo.panda.config;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.http.ResponseEntity;

/**
 * This interface represents the service for Panda configuration.
 * It provides methods to download and verify the Panda configuration.
 */

public interface PandaConfigService {

    /**
     * This method is used to download the Panda configuration.
     * It takes the ID of the Panda device as a parameter and returns a ResponseEntity containing the configuration as a byte array.
     *
     * @param pandaId the ID of the Panda device
     * @return ResponseEntity containing the configuration as a byte array
     */
    ResponseEntity<byte[]> downloadPandaConfig(Long pandaId);

    /**
     * This method is used to verify the Panda configuration.
     * It takes the JSON string of the Panda configuration as a parameter and returns a ResponseEntity containing the ApiResponseDto.
     *
     * @param pandaConfigJson the JSON string of the Panda configuration
     * @return ResponseEntity containing the ApiResponseDto
     */
    ResponseEntity<ApiResponseDto<?>> verifyPandaConfig(String pandaConfigJson);

}