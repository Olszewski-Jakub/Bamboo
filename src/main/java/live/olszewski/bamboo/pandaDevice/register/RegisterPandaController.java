package live.olszewski.bamboo.pandaDevice.register;

import live.olszewski.bamboo.user.UserService;
import live.olszewski.bamboo.uuid.UUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping(path = "#{apiConfig.registerPandaPath}")
public class RegisterPandaController {

    private final RegisterPandaService registerPandaService;
    @Autowired
    public RegisterPandaController(RegisterPandaService registerPandaService) {
        this.registerPandaService = registerPandaService;
    }

    @PostMapping
    public void registerNewPandaDevice(@RequestBody RegisterPanda registerPanda) {

        registerPandaService.addPandaDevice(registerPanda);
    }

    @DeleteMapping(path = "{pandaId}")
    public void deletePandaDevice(@PathVariable("pandaId") Long id) {
        registerPandaService.deletePandaDevice(id);
    }

}
