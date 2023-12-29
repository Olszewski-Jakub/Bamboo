package live.olszewski.bamboo.panda.dataCollection;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface DataCollectionService {

    Long getPandaIdFromUUID(String uuid);

    ResponseEntity<ApiResponseDto<?>> sendDataPacket(DataPacketReceive dataPacketReceive);

}
