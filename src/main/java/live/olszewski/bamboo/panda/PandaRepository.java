package live.olszewski.bamboo.panda;

import live.olszewski.bamboo.panda.objects.PandaDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This interface extends JpaRepository and provides methods for querying the PandaDao table.
 */
@Repository
public interface PandaRepository extends JpaRepository<PandaDao,Long> {

    /**
     * Retrieves a PandaDao object by its UUID.
     *
     * @param UID The UUID of the PandaDao object.
     * @return An Optional containing the PandaDao object if it exists, empty otherwise.
     */
    @Query("SELECT s FROM PandaDao s WHERE s.uuid = ?1")
    Optional<PandaDao> findDeviceByUUID(String UID);

    /**
     * Retrieves all PandaDao objects associated with a specific owner.
     *
     * @param owner The owner of the PandaDao objects.
     * @return A List of PandaDao objects associated with the owner.
     */
    @Query("SELECT s FROM PandaDao s WHERE s.owner = ?1")
    List<PandaDao> findDeviceByOwner(Long owner);

    /**
     * Retrieves a PandaDao object by its ID.
     *
     * @param id The ID of the PandaDao object.
     * @return An Optional containing the PandaDao object if it exists, empty otherwise.
     */
    @Query("SELECT s FROM PandaDao s WHERE s.id = ?1")
    Optional<PandaDao> findDeviceById(Long id);

}
