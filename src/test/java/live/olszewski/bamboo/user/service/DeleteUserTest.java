package live.olszewski.bamboo.user.service;

import live.olszewski.bamboo.BaeldungPostgresqlContainer;
import live.olszewski.bamboo.testUtils.TestUtils;
import live.olszewski.bamboo.user.UserService;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class DeleteUserTest {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private UserService userService;


    @ClassRule
    public static PostgreSQLContainer<BaeldungPostgresqlContainer> postgreSQLContainer = BaeldungPostgresqlContainer.getInstance();

    @BeforeEach
    public void clearDatabase() {
        testUtils.clearUserDatabase();
    }

    @Test
    public void deleteUser_DeletesUserFromRepository() {
        testUtils.addUsersToDatabase(1);
        Long id = 1L;
        userService.deleteUser(id);
        assertThrows(IllegalStateException.class, () -> userService.getUserById(id));

    }
    @Test
    public void deleteUser_ThrowsExceptionWhenUserDoesNotExist() {
        Long nonExistentId = 100L;
        assertThrows(IllegalStateException.class, () -> userService.deleteUser(nonExistentId));
    }

    @Test
    public void deleteUser_DeletesCorrectUserWhenMultipleUsersExist() {
        testUtils.addUsersToDatabase(3);
        Long idToDelete = 2L;
        userService.deleteUser(idToDelete);
        assertThrows(IllegalStateException.class, () -> userService.getUserById(idToDelete));
        // Verify the other users still exist
        userService.getUserById(1L);
        userService.getUserById(3L);
    }
}
