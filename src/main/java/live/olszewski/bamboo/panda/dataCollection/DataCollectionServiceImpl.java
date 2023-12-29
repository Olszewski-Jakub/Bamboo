package live.olszewski.bamboo.panda.dataCollection;

import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.auth.pandaStorage.PandaStorage;
import live.olszewski.bamboo.panda.PandaDao;
import live.olszewski.bamboo.panda.PandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DataCollectionServiceImpl implements DataCollectionService {

    @Autowired
    private PandaRepository pandaRepository;

    @Autowired
    private DataPacketRepository dataPacketRepository;

    @Autowired
    private PandaStorage pandaStorage;

    @Autowired
    private ApiResponseBuilder apiResponseBuilder;

    @Override
    public Long getPandaIdFromUUID(String uuid) {
        PandaDao pandaDao = pandaRepository.findDeviceByUUID(uuid).orElse(null);
        return pandaDao != null ? pandaDao.getId() : null;
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> sendDataPacket(DataPacketReceive dataPacketReceive) {
        DataPacketDao dataPacketDao;
        try {
            dataPacketDao = dataPacketReceive.toDao();
            dataPacketRepository.save(dataPacketDao);
        } catch (Exception e) {
            return ResponseEntity.ok(apiResponseBuilder.buildErrorResponse(500, e.getMessage()));
        }

        return ResponseEntity.ok(apiResponseBuilder.buildSuccessResponse(200, "Data packet saved successfully", dataPacketDao));
    }

}
