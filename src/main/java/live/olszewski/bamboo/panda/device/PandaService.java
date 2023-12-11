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
import java.util.Optional;


public interface PandaService {

    public List<PandaDto> getAllPandaDevices();

    public List<PandaDto> getPandaDevicesByOwner();

    public Boolean updatePandaApiKey(Long pandaId, String apiKey);

}
