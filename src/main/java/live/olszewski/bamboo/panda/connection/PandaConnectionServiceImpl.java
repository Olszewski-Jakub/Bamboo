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


    @Override
    public ResponseEntity<ApiResponseDto<?>> checkConnectionStatusWithUuid(String UUID) {

        Optional<PandaStatusDao> pandaStatusDao = pandaStatusRepository.findByUUID(UUID);
        return pandaStatusDao.<ResponseEntity<ApiResponseDto<?>>>map(statusDao ->
                        ResponseEntity.ok(apiResponseBuilder.buildSuccessResponse(200, "Check device connection status successfully", statusDao.toDto())))
                .orElseGet(() -> ResponseEntity.status(500).body(apiResponseBuilder.buildErrorResponse(500, "Panda with UUID: " + UUID + " does not exist")));
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> checkConnectionStatusWithId(Long id) {
        Optional<PandaStatusDao> pandaStatusDao = pandaStatusRepository.findById(id);
        return pandaStatusDao.<ResponseEntity<ApiResponseDto<?>>>map(statusDao ->
                        ResponseEntity.ok(apiResponseBuilder.buildSuccessResponse(200, "Check device connection status successfully", statusDao.toDto())))
                .orElseGet(() -> ResponseEntity.status(500).body(apiResponseBuilder.buildErrorResponse(500, "Panda with id: " + id + " does not exist")));
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> sendConnectionStatus(String UUID, PandaStatus status) {
        PandaStatusDao pandaStatus = new PandaStatusDao(UUID, LocalDateTime.now(), status);
        pandaStatusRepository.save(pandaStatus);
        return ResponseEntity.ok(apiResponseBuilder.buildSuccessResponse(200, "Send device connection status successfully", null));
    }
}