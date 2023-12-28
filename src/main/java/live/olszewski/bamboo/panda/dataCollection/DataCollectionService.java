package live.olszewski.bamboo.panda.dataCollection;

import org.springframework.stereotype.Service;

@Service
public interface DataCollectionService {

    Long getPandaIdFromUUID(String uuid);

}
