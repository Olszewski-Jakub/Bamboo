package live.olszewski.bamboo.pandaDevice.register;

import live.olszewski.bamboo.user.UserDao;
import live.olszewski.bamboo.user.UserService;
import live.olszewski.bamboo.uuid.UUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Long.parseLong;

@Service
public class RegisterPandaService {

    private final RegisterPandaRepository registerPandaRepository;
    private final UUIDService uuidService;
    private final UserService userService;
    @Autowired
    public RegisterPandaService(RegisterPandaRepository registerPandaRepository, UUIDService uuidService, UserService userService) {
        this.registerPandaRepository = registerPandaRepository;
        this.uuidService = uuidService;
        this.userService = userService;
    }

    public void addPandaDevice(RegisterPanda registerPanda) {
        RegisterPandaDao registerPandaDao = new RegisterPandaDao();
        registerPandaDao.setLocation(registerPanda.getLocation());
        registerPandaDao.setName(registerPanda.getName());
        registerPandaDao.setStatus(true);
        registerPandaDao.setOwner(userService.getPandaOwner());
        registerPandaDao.setUuid(uuidService.generateUUIDFromString(
                registerPandaDao.valuesForUuidGeneration()
        ).toString());
        Optional<RegisterPandaDao> registerPandaDaoOptional = registerPandaRepository.findDeviceByUUID(registerPandaDao.getUuid());
        if (registerPandaDaoOptional.isPresent()) {
            throw new IllegalStateException("Device with this parameters already exists");
        }

        registerPandaRepository.save(registerPandaDao);
    }

    public void deletePandaDevice(Long id) {
        boolean exists = registerPandaRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Device with id " + id + " deos not exists");
        }
        registerPandaRepository.deleteById(id);
    }
}
