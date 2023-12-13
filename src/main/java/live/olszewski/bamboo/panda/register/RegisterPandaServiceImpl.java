package live.olszewski.bamboo.panda.register;

import live.olszewski.bamboo.panda.PandaDao;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.services.uuid.UUIDService;
import live.olszewski.bamboo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
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

    @Override
    public void deletePandaDevice(Long id) {
        boolean exists = pandaRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Device with id " + id + " deos not exists");
        }
        pandaRepository.deleteById(id);
    }

}
