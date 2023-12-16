package live.olszewski.bamboo.panda.register;

import live.olszewski.bamboo.panda.PandaDao;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.user.UserService;
import live.olszewski.bamboo.services.uuid.UUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.Long.parseLong;


public interface RegisterPandaService {

    void addPandaDevice(RegisterPanda registerPanda);
    void deletePandaDevice(Long id);

}
