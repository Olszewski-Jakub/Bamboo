package live.olszewski.bamboo.pandaDevice.register;

import live.olszewski.bamboo.user.UserDao;
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

    @Autowired
    public RegisterPandaService(RegisterPandaRepository registerPandaRepository, UUIDService uuidService) {
        this.registerPandaRepository = registerPandaRepository;
        this.uuidService = uuidService;
    }

    public void addPandaDevice(RegisterPandaDao registerPandaDao) {
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
