package live.olszewski.bamboo.pandaDevice.register;

import live.olszewski.bamboo.pandaDevice.PandaDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface RegisterPandaRepository extends JpaRepository<PandaDao,Long> {

    @Query("SELECT s FROM PandaDao s WHERE s.uuid = ?1")
    Optional<PandaDao> findDeviceByUUID(String UID);

}
