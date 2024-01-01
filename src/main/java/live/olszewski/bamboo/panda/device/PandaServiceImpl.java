package live.olszewski.bamboo.panda.device;

import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.apiResponse.MessageService;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.panda.objects.PandaDao;
import live.olszewski.bamboo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PandaServiceImpl implements PandaService {

    @Autowired
    private PandaRepository pandaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserStorage userStorage;

    @Autowired
    private ApiResponseBuilder builder;

    @Autowired
    private MessageService messageService;


    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllPandaDevices() {
        if (!userStorage.isAdministrator())
            return builder.error().code403(messageService.getMessage("user.not.administrator"));

        return builder.success().code200(messageService.getMessage("panda.retrieve.all"), pandaRepository.findAll().stream().map(PandaDao::toDto).toList());
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getPandaDevicesByOwner() {
        var ownedPandas = pandaRepository.findDeviceByOwner(userService.getPandaOwner()).stream().map(PandaDao::toDto).toList();
        return builder.success().code200(messageService.getMessage("panda.retrieve.by.owner", userService.getPandaOwner()), ownedPandas);
    }

    @Override
    public Boolean updatePandaApiKey(Long pandaId, String apiKey) {
        return pandaRepository.findById(pandaId).map(panda -> {
            panda.setApi_key(apiKey);
            pandaRepository.save(panda);
            return true;
        }).orElse(false);
    }

}
