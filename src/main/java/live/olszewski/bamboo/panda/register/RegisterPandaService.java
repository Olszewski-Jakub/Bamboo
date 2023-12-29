package live.olszewski.bamboo.panda.register;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.http.ResponseEntity;

/**
 * This interface defines methods for registering and deleting Panda devices.
 */
public interface RegisterPandaService {

    /**
     * Registers a new Panda device.
     *
     * @param registerPanda The RegisterPanda object containing the details of the new Panda device.
     * @return A ResponseEntity containing the result of the operation.
     */
    ResponseEntity<ApiResponseDto<?>> addPandaDevice(RegisterPanda registerPanda);

    /**
     * Deletes a Panda device.
     *
     * @param id The ID of the Panda device to delete.
     * @return A ResponseEntity containing the result of the operation.
     */
    ResponseEntity<ApiResponseDto<?>> deletePandaDevice(Long id);

}