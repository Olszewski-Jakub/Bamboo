package live.olszewski.bamboo.panda.device;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.http.ResponseEntity;

/**
 * This interface defines methods for managing Panda devices.
 */
public interface PandaService {

    /**
     * Retrieves all Panda devices.
     *
     * @return A ResponseEntity containing a list of all Panda devices.
     */
    ResponseEntity<ApiResponseDto<?>> getAllPandaDevices();

    /**
     * Retrieves all Panda devices owned by the current user.
     *
     * @return A ResponseEntity containing a list of all Panda devices owned by the current user.
     */
    ResponseEntity<ApiResponseDto<?>> getPandaDevicesByOwner();

    /**
     * Updates the API key of a Panda device.
     *
     * @param pandaId The ID of the Panda device.
     * @param apiKey  The new API key.
     * @return True if the API key was updated successfully, false otherwise.
     */
    Boolean updatePandaApiKey(Long pandaId, String apiKey);

}