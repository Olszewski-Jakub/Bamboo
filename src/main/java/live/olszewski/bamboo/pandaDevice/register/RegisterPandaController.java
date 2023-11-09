package live.olszewski.bamboo.pandaDevice.register;

import live.olszewski.bamboo.uuid.UUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "#{apiConfig.registerPandaPath}")
public class RegisterPandaController {

    private final RegisterPandaService registerPandaService;
    private final UUIDService uuidService;

    @Autowired
    public RegisterPandaController(RegisterPandaService registerPandaService, UUIDService uuidService) {
        this.registerPandaService = registerPandaService;
        this.uuidService = uuidService;
    }

    @PostMapping
    public void registerNewPandaDevice(@RequestBody RegisterPandaDao registerPandaDao) {

        registerPandaDao.setUuid(uuidService.generateUUIDFromString(
                registerPandaDao.getId().toString() + registerPandaDao.getName()
        ).toString());

    }

    @DeleteMapping(path = "{pandaId}")
    public void deletePandaDevice(@PathVariable("pandaId") Long id) {
        registerPandaService.deletePandaDevice(id);
    }

}
