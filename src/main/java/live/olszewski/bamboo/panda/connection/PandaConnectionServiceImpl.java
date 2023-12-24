package live.olszewski.bamboo.panda.connection;

import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.panda.connection.models.PandaStatus;
import live.olszewski.bamboo.panda.connection.models.PandaStatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service class for handling Panda device connections.
 */
@Service
public class PandaConnectionServiceImpl implements PandaConnectionService {

    @Autowired
    private PandaStatusRepository pandaStatusRepository;

    @Autowired
    private ApiResponseBuilder apiResponseBuilder;

    /**
     * Checks the connection status of a Panda device using its UUID.
     *
     * @param UUID the UUID of the Panda device
     * @return a PandaStatusDto object representing the connection status of the Panda device
     * @throws IllegalStateException if no Panda device with the given UUID exists
     */
    @Override
    public ResponseEntity<ApiResponseDto<?>> checkConnectionStatusWithUuid(String UUID) {

        Optional<PandaStatusDao> pandaStatusDao = pandaStatusRepository.findByUUID(UUID);
        return pandaStatusDao.<ResponseEntity<ApiResponseDto<?>>>map(statusDao -> ResponseEntity.ok(apiResponseBuilder.buildSuccessResponse(200, "Check device connection status successfully", statusDao.toDto()))).orElseGet(() -> ResponseEntity.status(500).body(apiResponseBuilder.buildErrorResponse(500, "Panda with UUID: " + UUID + " does not exist")));
    }

    /**
     * Checks the connection status of a Panda device using its ID.
     *
     * @param id the ID of the Panda device
     * @return a PandaStatusDto object representing the connection status of the Panda device
     * @throws IllegalStateException if no Panda device with the given ID exists
     */
    @Override
    public ResponseEntity<ApiResponseDto<?>> checkConnectionStatusWithId(Long id) {
        Optional<PandaStatusDao> pandaStatusDao = pandaStatusRepository.findById(id);
        if (pandaStatusDao.isEmpty())
            return ResponseEntity.status(500).body(apiResponseBuilder.buildErrorResponse(500, "Panda with id: " + id.toString() + " does not exist"));
        return ResponseEntity.ok(apiResponseBuilder.buildSuccessResponse(200, "Check device connection status successfully", pandaStatusDao.get().toDto()));
    }

    /**
     * Sends the connection status of a Panda device to the repository.
     *
     * @param UUID   the UUID of the Panda device
     * @param status the connection status of the Panda device
     */
    @Override
    public ResponseEntity<ApiResponseDto<?>> sendConnectionStatus(String UUID, PandaStatus status) {
        PandaStatusDao pandaStatus = new PandaStatusDao(UUID, LocalDateTime.now(), status);
        pandaStatusRepository.save(pandaStatus);
        return ResponseEntity.ok(apiResponseBuilder.buildSuccessResponse(200, "Send device connection status successfully", null));
    }
}