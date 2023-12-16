package live.olszewski.bamboo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface is a Spring Data JPA repository for the UserDao entity.
 * It extends JpaRepository which provides methods for CRUD operations.
 * It uses the @Repository annotation to indicate that it is a repository.
 */
@Repository
public interface UserRepository extends JpaRepository<UserDao,Long> {

    /**
     * This method finds a user by their email.
     * It uses a custom query to select the user from the UserDao table where the email matches the given email.
     *
     * @param email the email of the user
     * @return an Optional of UserDao if a user with the given email exists, empty Optional otherwise
     */
    @Query("SELECT s FROM UserDao s WHERE s.email = ?1")
    Optional<UserDao> findUserByEmail(String email);

    /**
     * This method finds a user by their id.
     * It uses a custom query to select the user from the UserDao table where the id matches the given id.
     *
     * @param id the id of the user
     * @return an Optional of UserDao if a user with the given id exists, empty Optional otherwise
     */
    @Query("SELECT s FROM UserDao s WHERE s.id = ?1")
    Optional<UserDao> findUserById(Long id);
}
