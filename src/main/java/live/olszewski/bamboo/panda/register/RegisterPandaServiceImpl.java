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

    @Autowired
    private PandaRepository pandaRepository;

    @Autowired
    private UUIDService uuidService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApiResponseBuilder builder;

    @Autowired
    private MessageService messageService;

    @Override
    public ResponseEntity<ApiResponseDto<?>> addPandaDevice(RegisterPanda registerPanda) {
        PandaDao registerPandaDao = new PandaDao();
        registerPandaDao.setLocation(registerPanda.getLocation());
        registerPandaDao.setName(registerPanda.getName());
        registerPandaDao.setStatus(true);
        registerPandaDao.setOwner(userService.getPandaOwner());
        registerPandaDao.setUuid(uuidService.generateUUIDFromString(registerPandaDao.valuesForUuidGeneration()).toString());
        Optional<PandaDao> registerPandaDaoOptional = pandaRepository.findDeviceByUUID(registerPandaDao.getUuid());
        if (registerPandaDaoOptional.isPresent()) {
            return builder.error().code409(messageService.getMessage("panda.exists.", registerPandaDao.getId()));
        }

        pandaRepository.save(registerPandaDao);
        return builder.success().code200(messageService.getMessage("panda.created", registerPandaDao.getId()), null);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> deletePandaDevice(Long id) {
        boolean exists = pandaRepository.existsById(id);
        if (!exists) {
            return builder.error().code404(messageService.getMessage("panda.not.found", id));
        }
        pandaRepository.deleteById(id);
        return builder.success().code200(messageService.getMessage("panda.deleted", id), null);
    }

}
