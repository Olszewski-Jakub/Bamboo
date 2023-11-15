package live.olszewski.bamboo.panda.device;

import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.panda.PandaDao;
import live.olszewski.bamboo.panda.PandaDto;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PandaService {
    private final PandaRepository pandaRepository;
    private final UserService userService;

    private final UserStorage userStorage;
    @Autowired
    public PandaService(PandaRepository pandaRepository, UserService userService, UserStorage userStorage) {
        this.userService = userService;
        this.pandaRepository = pandaRepository;
        this.userStorage = userStorage;
    }


    public List<PandaDto> getAllPandaDevices() {
        if (!userStorage.isAdministrator())
            throw new AccessDeniedException("User is not administrator");
        return pandaRepository.findAll().stream().map(PandaDao::toDto).toList();
    }

    public List<PandaDto> getPandaDevicesByOwner() {
        return pandaRepository.findDeviceByOwner(userService.getPandaOwner()).stream().map(PandaDao::toDto).toList();
    }



}
