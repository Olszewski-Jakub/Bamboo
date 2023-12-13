package live.olszewski.bamboo.panda.device;

import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.panda.PandaDao;
import live.olszewski.bamboo.panda.PandaDto;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.user.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PandaServiceImpl implements PandaService{

    @Autowired
    private PandaRepository pandaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserStorage userStorage;


    @Override
    public List<PandaDto> getAllPandaDevices() {
        if (!userStorage.isAdministrator())
            throw new AccessDeniedException("User is not administrator");
        return pandaRepository.findAll().stream().map(PandaDao::toDto).toList();
    }
    @Override
    public List<PandaDto> getPandaDevicesByOwner() {
        return pandaRepository.findDeviceByOwner(userService.getPandaOwner()).stream().map(PandaDao::toDto).toList();
    }
@Override
    public Boolean updatePandaApiKey(Long pandaId, String apiKey){
        Optional<PandaDao> pandaDao = pandaRepository.findById(pandaId);
        if(pandaDao.isEmpty())
            return false;
        pandaDao.get().setApi_key(apiKey);
        pandaRepository.save(pandaDao.get());
        return true;
    }

}
