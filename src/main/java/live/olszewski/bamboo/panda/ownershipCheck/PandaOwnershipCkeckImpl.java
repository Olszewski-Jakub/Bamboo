package live.olszewski.bamboo.panda.ownershipCheck;

import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.panda.PandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PandaOwnershipCkeckImpl implements PandaOwnershipCkeck {

    @Autowired
    UserStorage userStorage;

    @Autowired
    private PandaRepository pandaRepository;

    @Override
    public boolean isPandaOwner(Long pandaId) {
        return pandaRepository.findDeviceById(pandaId)
                .map(pandaDao -> pandaDao.getOwner().equals(userStorage.getCurrentUserId()))
                .orElseThrow(() -> new IllegalStateException("Device with id " + pandaId + " does not exists"));
    }
}