package live.olszewski.bamboo.panda.register;

import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
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
    private ApiResponseBuilder apiResponseBuilder;

    @Override
    public ResponseEntity<ApiResponseDto<?>> addPandaDevice(RegisterPanda registerPanda) {
        PandaDao registerPandaDao = new PandaDao();
        registerPandaDao.setLocation(registerPanda.getLocation());
        registerPandaDao.setName(registerPanda.getName());
        registerPandaDao.setStatus(true);
        registerPandaDao.setOwner(userService.getPandaOwner());
        registerPandaDao.setUuid(uuidService.generateUUIDFromString(
                registerPandaDao.valuesForUuidGeneration()
        ).toString());
        Optional<PandaDao> registerPandaDaoOptional = pandaRepository.findDeviceByUUID(registerPandaDao.getUuid());
        if (registerPandaDaoOptional.isPresent()) {
            return ResponseEntity.status(500).body(apiResponseBuilder.buildErrorResponse(500, "Device with this parameters already exists"));
        }

        pandaRepository.save(registerPandaDao);
        return ResponseEntity.ok(apiResponseBuilder.buildSuccessResponse(200, "Device registered successfully", null));
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> deletePandaDevice(Long id) {
        boolean exists = pandaRepository.existsById(id);
        if (!exists) {
            return ResponseEntity.status(500).body(apiResponseBuilder.buildErrorResponse(500, "Device with id " + id + " deos not exists"));
        }
        pandaRepository.deleteById(id);
        return ResponseEntity.ok(apiResponseBuilder.buildSuccessResponse(200, "Device deleted successfully", null));
    }

}
