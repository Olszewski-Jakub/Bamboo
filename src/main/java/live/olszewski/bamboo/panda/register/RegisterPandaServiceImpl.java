package live.olszewski.bamboo.panda.register;

import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.apiResponse.MessageService;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.panda.objects.PandaDao;
import live.olszewski.bamboo.services.uuid.UUIDService;
import live.olszewski.bamboo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterPandaServiceImpl implements RegisterPandaService {

    private final PandaRepository pandaRepository;
    private final UUIDService uuidService;
    private final UserService userService;
    private final ApiResponseBuilder builder;
    private final MessageService messageService;

    @Autowired
    public RegisterPandaServiceImpl(PandaRepository pandaRepository, UUIDService uuidService, UserService userService, ApiResponseBuilder builder, MessageService messageService) {
        this.pandaRepository = pandaRepository;
        this.uuidService = uuidService;
        this.userService = userService;
        this.builder = builder;
        this.messageService = messageService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> addPandaDevice(RegisterPanda registerPanda) {
        PandaDao registerPandaDao = buildPandaModel(registerPanda);
        Optional<PandaDao> registerPandaDaoOptional = pandaRepository.findDeviceByUUID(registerPandaDao.getUuid());
        if (registerPandaDaoOptional.isPresent()) {
            return builder.error().code409(messageService.getMessage("panda.exists.", registerPandaDao.getId()));
        }

        savePanda(registerPandaDao);
        return builder.success().code200(messageService.getMessage("panda.created", registerPandaDao.getId()), null);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> deletePandaDevice(Long id) {
        return Optional.of(retrievePandaFromDatabase(id))
                .map(panda -> {
                    deletePanda(id);
                    return builder.success().code200(messageService.getMessage("panda.deleted", id), null);
                })
                .orElseGet(() -> builder.error().code404(messageService.getMessage("panda.not.found", id)));
    }

    private void savePanda(PandaDao registerPandaDao) {
        pandaRepository.save(registerPandaDao);
    }

    private void deletePanda(Long id) {
        pandaRepository.deleteById(id);
    }

    private Optional<PandaDao> retrievePandaFromDatabase(Long id) {
        return pandaRepository.findById(id);
    }

    private PandaDao buildPandaModel(RegisterPanda registerPanda) {
        PandaDao registerPandaDao = new PandaDao();
        registerPandaDao.setLocation(registerPanda.getLocation());
        registerPandaDao.setName(registerPanda.getName());
        registerPandaDao.setStatus(true);
        registerPandaDao.setOwner(userService.getPandaOwner());
        registerPandaDao.setUuid(uuidService.generateUUIDFromString(registerPandaDao.valuesForUuidGeneration()).toString());
        return registerPandaDao;
    }
}
