package live.olszewski.bamboo.panda.register;

import live.olszewski.bamboo.panda.PandaDao;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.user.UserService;
import live.olszewski.bamboo.uuid.UUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.Long.parseLong;

@Service
public class RegisterPandaService {

    private final PandaRepository pandaRepository;
    private final UUIDService uuidService;
    private final UserService userService;
    @Autowired
    public RegisterPandaService(PandaRepository registerPandaRepository, UUIDService uuidService, UserService userService) {
        this.pandaRepository = registerPandaRepository;
        this.uuidService = uuidService;
        this.userService = userService;
    }

    public void addPandaDevice(RegisterPanda registerPanda) {
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
            throw new IllegalStateException("Device with this parameters already exists");
        }

        pandaRepository.save(registerPandaDao);
    }

    public void deletePandaDevice(Long id) {
        boolean exists = pandaRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Device with id " + id + " deos not exists");
        }
        pandaRepository.deleteById(id);
    }
}
