package live.olszewski.bamboo.panda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PandaRepository extends JpaRepository<PandaDao,Long> {

    @Query("SELECT s FROM PandaDao s WHERE s.uuid = ?1")
    Optional<PandaDao> findDeviceByUUID(String UID);

    @Query("SELECT s FROM PandaDao s WHERE s.owner = ?1")
    List<PandaDao> findDeviceByOwner(Long owner);

    @Query("SELECT s FROM PandaDao s WHERE s.id = ?1")
    Optional<PandaDao> findDeviceById(Long id);

}
