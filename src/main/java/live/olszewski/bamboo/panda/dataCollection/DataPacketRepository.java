package live.olszewski.bamboo.panda.dataCollection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataPacketRepository extends JpaRepository<DataPacketDao, Long> {

    @Query("SELECT u FROM DataPacketDao u WHERE u.id = ?1")
    Optional<DataPacketDao> findById(long id);

}
