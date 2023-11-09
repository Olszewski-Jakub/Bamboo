package live.olszewski.bamboo.uuid;

import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UUIDServiceImpl implements UUIDService {

    @Override
    public UUID generateUUIDFromString(String inputString) {
        return UUID.nameUUIDFromBytes(inputString.getBytes());
    }
}
