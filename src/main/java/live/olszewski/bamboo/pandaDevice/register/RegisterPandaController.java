package live.olszewski.bamboo.pandaDevice.register;

import live.olszewski.bamboo.uuid.UUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "#{apiConfig.registerPandaPath}")
public class RegisterPandaController {

    private final RegisterPandaService registerPandaService;

    @Autowired
    public RegisterPandaController(RegisterPandaService registerPandaService, UUIDService uuidService) {
        this.registerPandaService = registerPandaService;
    }

    @PostMapping
    public void registerNewPandaDevice(@RequestBody Map<String, Object> requestData) {
        String location = (String) requestData.get("location");
        String name = (String) requestData.get("name");
        Boolean status = (Boolean) requestData.get("status");
        Long owner = Long.parseLong(requestData.get("owner").toString());
        RegisterPandaDao registerPandaDao = new RegisterPandaDao(location, name, status, owner);
        registerPandaService.addPandaDevice(registerPandaDao);
    }

    @DeleteMapping(path = "{pandaId}")
    public void deletePandaDevice(@PathVariable("pandaId") Long id) {
        registerPandaService.deletePandaDevice(id);
    }

}
