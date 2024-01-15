package live.olszewski.bamboo.panda.dataPacketCollection.collection;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * This interface defines methods for managing data collection from Panda devices.
 */
@Service
public interface DataCollectionService {

    /**
     * Retrieves the ID of a Panda device using its UUID.
     *
     * @param uuid The UUID of the Panda device.
     * @return The ID of the Panda device.
     */
    Long getPandaIdFromUUID(String uuid);

    /**
     * Sends a data packet with the provided people count.
     *
     * @param peopleCount The number of people to include in the data packet.
     * @return A ResponseEntity containing the result of the operation.
     */
    ResponseEntity<ApiResponseDto<?>> sendDataPacket(int peopleCount);
}