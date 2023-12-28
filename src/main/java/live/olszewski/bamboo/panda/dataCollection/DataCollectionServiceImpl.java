package live.olszewski.bamboo.panda.dataCollection;

import live.olszewski.bamboo.panda.PandaDao;
import live.olszewski.bamboo.panda.PandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataCollectionServiceImpl implements DataCollectionService {

    @Autowired
    private PandaRepository pandaRepository;


    @Override
    public Long getPandaIdFromUUID(String uuid) {
        PandaDao pandaDao = pandaRepository.findDeviceByUUID(uuid).orElse(null);
        return pandaDao != null ? pandaDao.getId() : null;
    }
}
