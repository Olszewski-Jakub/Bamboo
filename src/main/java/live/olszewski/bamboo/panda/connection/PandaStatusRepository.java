package live.olszewski.bamboo.panda.connection;

import live.olszewski.bamboo.panda.connection.models.PandaStatusDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PandaStatusRepository extends JpaRepository<PandaStatusDao, Long> {

    @Query("SELECT s FROM PandaStatusDao s WHERE s.id = ?1")
    Optional<PandaStatusDao> findById(Long id);

    @Query("SELECT s FROM PandaStatusDao s WHERE s.UUID = ?1")
    Optional<PandaStatusDao> findByUUID(String UUID);

}
