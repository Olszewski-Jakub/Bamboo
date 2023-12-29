package live.olszewski.bamboo.panda.connection;

import live.olszewski.bamboo.panda.connection.models.PandaStatusDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * This interface extends JpaRepository and provides methods for querying the PandaStatusDao table.
 */
public interface PandaStatusRepository extends JpaRepository<PandaStatusDao, Long> {

    /**
     * Retrieves a PandaStatusDao object by its ID.
     *
     * @param id The ID of the PandaStatusDao object.
     * @return An Optional containing the PandaStatusDao object if it exists, empty otherwise.
     */
    @Query("SELECT s FROM PandaStatusDao s WHERE s.id = ?1")
    Optional<PandaStatusDao> findById(Long id);

    /**
     * Retrieves a PandaStatusDao object by its UUID.
     *
     * @param UUID The UUID of the PandaStatusDao object.
     * @return An Optional containing the PandaStatusDao object if it exists, empty otherwise.
     */
    @Query("SELECT s FROM PandaStatusDao s WHERE s.UUID = ?1")
    Optional<PandaStatusDao> findByUUID(String UUID);

}
