package live.olszewski.bamboo.panda.device;

import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.apiResponse.MessageService;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.panda.objects.PandaDao;
import live.olszewski.bamboo.panda.objects.PandaDto;
import live.olszewski.bamboo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PandaServiceImpl implements PandaService {


    private final PandaRepository pandaRepository;
    private final UserService userService;
    private final UserStorage userStorage;
    private final ApiResponseBuilder builder;
    private final MessageService messageService;

    @Autowired
    public PandaServiceImpl(PandaRepository pandaRepository, UserService userService, UserStorage userStorage, ApiResponseBuilder builder, MessageService messageService) {
        this.pandaRepository = pandaRepository;
        this.userService = userService;
        this.userStorage = userStorage;
        this.builder = builder;
        this.messageService = messageService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllPandaDevices() {
        if (!userHaveAdminPrivileges())
            return builder.error().code403(messageService.getMessage("user.not.administrator"));

        return builder.success().code200(messageService.getMessage("panda.retrieve.all"), pandaRepository.findAll().stream().map(PandaDao::toDto).toList());
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getPandaDevicesByOwner() {
        return builder.success().code200(messageService.getMessage("panda.retrieve.by.owner", userService.getPandaOwner()), getAllPandaDeviceByUserFromDatabase());
    }

    @Override
    public Boolean updatePandaApiKey(Long pandaId, String apiKey) {
        return pandaRepository.findById(pandaId).map(panda -> {
            panda.setApi_key(apiKey);
            pandaRepository.save(panda);
            return true;
        }).orElse(false);
    }

    private Boolean userHaveAdminPrivileges() {
        return userStorage.isAdministrator();
    }

    private List<PandaDto> getAllPandaDeviceByUserFromDatabase() {
        return pandaRepository.findDeviceByOwner(userService.getPandaOwner()).stream().map(PandaDao::toDto).toList();
    }

}
