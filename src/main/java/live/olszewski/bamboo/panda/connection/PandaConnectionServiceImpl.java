package live.olszewski.bamboo.panda.connection;

import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.apiResponse.MessageService;
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
    private ApiResponseBuilder builder;

    @Autowired
    private MessageService messageService;


    @Override
    public ResponseEntity<ApiResponseDto<?>> checkConnectionStatusWithUuid(String UUID) {
        Optional<PandaStatusDao> pandaStatusDao = pandaStatusRepository.findByUUID(UUID);
        return pandaStatusDao.map(statusDao -> builder.success().code200(messageService.getMessage("panda.status.retrieve.uuid", UUID), statusDao.toDto())).orElseGet(() -> builder.error().code404(messageService.getMessage("panda.status.not.found.uuid", UUID)));
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> checkConnectionStatusWithId(Long id) {
        Optional<PandaStatusDao> pandaStatusDao = pandaStatusRepository.findById(id);
        return pandaStatusDao.map(statusDao -> builder.success().code200(messageService.getMessage("panda.status.retrieve.id", id), statusDao.toDto())).orElseGet(() -> builder.error().code404(messageService.getMessage("panda.status.not.found.id", id)));
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> sendConnectionStatus(String UUID, PandaStatus status) {
        PandaStatusDao pandaStatus = new PandaStatusDao(UUID, LocalDateTime.now(), status);
        pandaStatusRepository.save(pandaStatus);
        return builder.success().code200(messageService.getMessage("panda.status.send", UUID), null);
    }
}