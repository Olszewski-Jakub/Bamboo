package live.olszewski.bamboo.panda.connection;

import live.olszewski.bamboo.panda.connection.models.PandaStatus;
import live.olszewski.bamboo.panda.connection.models.PandaStatusDto;

public interface PandaConnectionService {

    void sendConnectionStatus(String UUID, PandaStatus status);

    PandaStatusDto checkConnectionStatusWithUuid(String UUID);

    PandaStatusDto checkConnectionStatusWithId(Long id);
}
