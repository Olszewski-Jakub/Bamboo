package live.olszewski.bamboo.pandaDevice.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterPandaService {

    private final RegisterPandaRepository registerPandaRepository;

    @Autowired
    public RegisterPandaService(RegisterPandaRepository registerPandaRepository) {
        this.registerPandaRepository = registerPandaRepository;
    }

    public void addPandaDevice(RegisterPandaDao registerPandaDao){
        registerPandaRepository.save(registerPandaDao);
    }

    public void deletePandaDevice(Long id){
        boolean exists = registerPandaRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Device with id " + id + " deos not exists");
        }
        registerPandaRepository.deleteById(id);
    }
}
