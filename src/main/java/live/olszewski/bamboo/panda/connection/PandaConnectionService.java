package live.olszewski.bamboo.panda.connection;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.panda.connection.models.PandaStatus;
import org.springframework.http.ResponseEntity;

/**
 * This interface defines methods for managing the connection status of Panda devices.
 */
public interface PandaConnectionService {

    /**
     * Sends the connection status of a Panda device.
     *
     * @param UUID   The UUID of the Panda device.
     * @param status The connection status of the Panda device.
     * @return A ResponseEntity containing the result of the operation.
     */
    ResponseEntity<ApiResponseDto<?>> sendConnectionStatus(String UUID, PandaStatus status);

    /**
     * Checks the connection status of a Panda device using its UUID.
     *
     * @param UUID The UUID of the Panda device.
     * @return A ResponseEntity containing the connection status of the Panda device.
     */
    ResponseEntity<ApiResponseDto<?>> checkConnectionStatusWithUuid(String UUID);

    /**
     * Checks the connection status of a Panda device using its ID.
     *
     * @param id The ID of the Panda device.
     * @return A ResponseEntity containing the connection status of the Panda device.
     */
    ResponseEntity<ApiResponseDto<?>> checkConnectionStatusWithId(Long id);
}
