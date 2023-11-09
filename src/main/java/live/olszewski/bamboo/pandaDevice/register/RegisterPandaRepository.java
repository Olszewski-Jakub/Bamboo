package live.olszewski.bamboo.pandaDevice.register;

import live.olszewski.bamboo.user.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface RegisterPandaRepository extends JpaRepository<RegisterPandaDao,Long> {

    @Query("SELECT s FROM RegisterPandaDao s WHERE s.uuid = ?1")
    Optional<UserDao> findDeviceByUUID(String UID);

}
