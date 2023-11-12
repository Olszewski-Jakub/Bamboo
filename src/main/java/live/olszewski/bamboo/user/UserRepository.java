package live.olszewski.bamboo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDao,Long> {

    @Query("SELECT s FROM UserDao s WHERE s.email = ?1")
    Optional<UserDao> findUserByEmail(String email);


}
