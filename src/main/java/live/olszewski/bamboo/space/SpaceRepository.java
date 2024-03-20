package live.olszewski.bamboo.space;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpaceRepository extends JpaRepository<SpaceDao, Long> {

    @Query("SELECT s FROM SpaceDao s JOIN s.userPrivileges u WHERE u.userId = ?1")
    List<SpaceDao> findByUserId(Long userId);
}
