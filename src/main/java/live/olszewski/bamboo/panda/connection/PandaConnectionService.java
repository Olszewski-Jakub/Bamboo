package live.olszewski.bamboo.panda.connection;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.panda.connection.models.PandaStatus;
import org.springframework.http.ResponseEntity;

public interface PandaConnectionService {

    ResponseEntity<ApiResponseDto<?>> sendConnectionStatus(String UUID, PandaStatus status);

    ResponseEntity<ApiResponseDto<?>> checkConnectionStatusWithUuid(String UUID);

    ResponseEntity<ApiResponseDto<?>> checkConnectionStatusWithId(Long id);
}
