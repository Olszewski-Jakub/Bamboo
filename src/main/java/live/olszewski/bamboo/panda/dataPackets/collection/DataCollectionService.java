package live.olszewski.bamboo.panda.dataPackets.collection;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface DataCollectionService {

    Long getPandaIdFromUUID(String uuid);

    ResponseEntity<ApiResponseDto<?>> sendDataPacket(int peopleCount);
}
