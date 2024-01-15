package live.olszewski.bamboo.panda.dataPacketCollection;

import live.olszewski.bamboo.panda.dataPacketCollection.objects.DataPacketDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This interface extends JpaRepository and provides methods for querying the DataPacketDao table.
 */
@Repository
public interface DataPacketRepository extends JpaRepository<DataPacketDao, Long> {

    /**
     * Retrieves a DataPacketDao object by its ID.
     *
     * @param id The ID of the DataPacketDao object.
     * @return An Optional containing the DataPacketDao object if it exists, empty otherwise.
     */
    @Query("SELECT u FROM DataPacketDao u WHERE u.id = ?1")
    Optional<DataPacketDao> findById(long id);

    /**
     * Retrieves all DataPacketDao objects associated with a specific Panda device.
     *
     * @param pandaId The ID of the Panda device.
     * @return A List of DataPacketDao objects associated with the Panda device.
     */
    @Query("SELECT u FROM DataPacketDao u WHERE u.pandaId = ?1")
    List<DataPacketDao> findAllByPandaId(Long pandaId);

}