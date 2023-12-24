package live.olszewski.bamboo.panda.device;

import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.panda.PandaDao;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PandaServiceImpl implements PandaService {

    @Autowired
    private PandaRepository pandaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserStorage userStorage;

    @Autowired
    private ApiResponseBuilder apiResponseBuilder;


    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllPandaDevices() {
        if (!userStorage.isAdministrator())
            return ResponseEntity.status(403).body(apiResponseBuilder.buildErrorResponse(403, "User is not administrator"));

        return ResponseEntity.ok(apiResponseBuilder.buildSuccessResponse(200, "Successfully retrieved all panda devices", pandaRepository.findAll().stream().map(PandaDao::toDto).toList()));
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getPandaDevicesByOwner() {
        return ResponseEntity.ok(apiResponseBuilder.buildSuccessResponse(200, "Successfully retrieved all panda devices owned by user", pandaRepository.findDeviceByOwner(userService.getPandaOwner()).stream().map(PandaDao::toDto).toList()));
    }

    @Override
    public Boolean updatePandaApiKey(Long pandaId, String apiKey) {
        Optional<PandaDao> pandaDao = pandaRepository.findById(pandaId);
        if (pandaDao.isEmpty())
            return false;
        pandaDao.get().setApi_key(apiKey);
        pandaRepository.save(pandaDao.get());
        return true;
    }

}
