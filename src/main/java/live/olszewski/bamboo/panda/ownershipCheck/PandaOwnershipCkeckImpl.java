package live.olszewski.bamboo.panda.ownershipCheck;

import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.panda.PandaDao;
import live.olszewski.bamboo.panda.PandaDto;
import live.olszewski.bamboo.panda.PandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PandaOwnershipCkeckImpl implements PandaOwnershipCkeck {


    @Autowired
    UserStorage userStorage;

    @Autowired
    private PandaRepository pandaRepository;

    @Override
    public boolean isPandaOwner(Long pandaId) {

        Optional<PandaDao> optionalPandaDao = pandaRepository.findDeviceById(pandaId);
        if (optionalPandaDao.isEmpty()) {
            throw new IllegalStateException("Device with id " + pandaId + " does not exists");
        }
        PandaDto pandaDto = optionalPandaDao.get().toDto();
        return pandaDto.getOwner().equals(userStorage.getCurrentUserId());
    }
}
