package live.olszewski.bamboo.apiKeys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApiKeysRepository extends JpaRepository<ApiKeyDao,Long> {

    @Query("SELECT u FROM ApiKeyDao u WHERE u.id = ?1")
    Optional<ApiKeyDao> findById(long id);

    @Query("SELECT u FROM ApiKeyDao u WHERE u.panda = ?1")
    List<ApiKeyDao> findByPanda(long panda);

    @Query("SELECT u FROM ApiKeyDao u WHERE u.owner = ?1")
    List<ApiKeyDao> findByOwner(long owner);

    @Query("SELECT u FROM ApiKeyDao u WHERE u.panda = ?1 AND u.active = true")
    Optional<ApiKeyDao> findByPandaAndActive(Long panda);
}
