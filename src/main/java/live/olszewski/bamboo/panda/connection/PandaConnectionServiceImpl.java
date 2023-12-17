package live.olszewski.bamboo.panda.connection;

import live.olszewski.bamboo.panda.connection.models.PandaStatus;
import live.olszewski.bamboo.panda.connection.models.PandaStatusDao;
import live.olszewski.bamboo.panda.connection.models.PandaStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service class for handling Panda device connections.
 */
@Service
public class PandaConnectionServiceImpl implements PandaConnectionService {

    @Autowired
    private PandaStatusRepository pandaStatusRepository;

    /**
     * Checks the connection status of a Panda device using its UUID.
     *
     * @param UUID the UUID of the Panda device
     * @return a PandaStatusDto object representing the connection status of the Panda device
     * @throws IllegalStateException if no Panda device with the given UUID exists
     */
    @Override
    public PandaStatusDto checkConnectionStatusWithUuid(String UUID) {

        Optional<PandaStatusDao> pandaStatusDao = pandaStatusRepository.findByUUID(UUID);
        if (pandaStatusDao.isEmpty())
            throw new IllegalStateException("Panda with UUID: " + UUID + " does not exist");

        return pandaStatusDao.get().toDto();
    }

    /**
     * Checks the connection status of a Panda device using its ID.
     *
     * @param id the ID of the Panda device
     * @return a PandaStatusDto object representing the connection status of the Panda device
     * @throws IllegalStateException if no Panda device with the given ID exists
     */
    @Override
    public PandaStatusDto checkConnectionStatusWithId(Long id) {
        Optional<PandaStatusDao> pandaStatusDao = pandaStatusRepository.findById(id);
        if (pandaStatusDao.isEmpty())
            throw new IllegalStateException("Panda with id: " + id.toString() + " does not exist");

        return pandaStatusDao.get().toDto();
    }

    /**
     * Sends the connection status of a Panda device to the repository.
     *
     * @param UUID   the UUID of the Panda device
     * @param status the connection status of the Panda device
     */
    @Override
    public void sendConnectionStatus(String UUID, PandaStatus status) {
        PandaStatusDao pandaStatus = new PandaStatusDao(UUID, LocalDateTime.now(), status);
        pandaStatusRepository.save(pandaStatus);
    }
}