package live.olszewski.bamboo.panda.dataPackets.collection;

import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.apiResponse.MessageService;
import live.olszewski.bamboo.auth.pandaStorage.PandaStorage;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.panda.dataPackets.DataPacketRepository;
import live.olszewski.bamboo.panda.dataPackets.objects.DataPacketDao;
import live.olszewski.bamboo.panda.objects.PandaDao;
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
    private ApiResponseBuilder builder;

    @Autowired
    private MessageService messageService;

    @Override
    public Long getPandaIdFromUUID(String uuid) {
        return pandaRepository.findDeviceByUUID(uuid).map(PandaDao::getId).orElse(null);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> sendDataPacket(int peopleCount) {
        DataPacketDao dataPacketDao;
        try {
            dataPacketDao = new DataPacketDao(pandaStorage.getPandaId(), peopleCount);
            dataPacketRepository.save(dataPacketDao);
        } catch (Exception e) {
            return builder.error().code500(messageService.getMessage("internal.server.error", e.getMessage()));
        }
        return builder.success().code200(messageService.getMessage("data.packet.saved"), dataPacketDao);
    }

}
