package live.olszewski.bamboo.apiKeys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This interface represents the API Keys Repository.
 * It extends JpaRepository to provide methods to manipulate API keys in the database.
 */
@Repository
public interface ApiKeysRepository extends JpaRepository<ApiKeyDao,Long> {

    /**
     * This method is used to find an API key by its ID.
     *
     * @param id The ID of the API key.
     * @return An Optional containing the API key if found, otherwise an empty Optional.
     */
    @Query("SELECT u FROM ApiKeyDao u WHERE u.id = ?1")
    Optional<ApiKeyDao> findById(long id);

    /**
     * This method is used to find API keys by the panda they are associated with.
     * @param panda The ID of the panda.
     * @return A list of API keys associated with the panda.
     */
    @Query("SELECT u FROM ApiKeyDao u WHERE u.panda = ?1")
    List<ApiKeyDao> findByPanda(long panda);

    /**
     * This method is used to find API keys by their owner.
     * @param owner The ID of the owner.
     * @return A list of API keys owned by the owner.
     */
    @Query("SELECT u FROM ApiKeyDao u WHERE u.owner = ?1")
    List<ApiKeyDao> findByOwner(long owner);

    /**
     * This method is used to find an active API key by the panda it is associated with.
     * @param panda The ID of the panda.
     * @return An Optional containing the active API key if found, otherwise an empty Optional.
     */
    @Query("SELECT u FROM ApiKeyDao u WHERE u.panda = ?1 AND u.active = true")
    Optional<ApiKeyDao> findByPandaAndActive(Long panda);

    /**
     * This method is used to find an API key by its key.
     * @param key The key of the API key.
     * @return An Optional containing the API key if found, otherwise an empty Optional.
     */
    @Query("SELECT u FROM ApiKeyDao u WHERE u.key = ?1")
    Optional<ApiKeyDao> findByKey(String key);
}
