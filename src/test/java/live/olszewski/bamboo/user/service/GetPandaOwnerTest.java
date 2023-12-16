package live.olszewski.bamboo.user.service;

import live.olszewski.bamboo.BaeldungPostgresqlContainer;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class GetPandaOwnerTest {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private UserStorage userStorage;

    @ClassRule
    public static PostgreSQLContainer<BaeldungPostgresqlContainer> postgreSQLContainer = BaeldungPostgresqlContainer.getInstance();

    @BeforeEach
    public void clearDatabase() {
        testUtils.clearUserDatabase();
    }

    @Test
    public void getPandaOwner_ReturnsCorrectOwnerId() {
        userStorage.setCurrentUser("test1", "user1@test.com", "UUID1", true, 1L);
        testUtils.addUsersToDatabase(1);
        Long expectedOwnerId = 1L;
        Long actualOwnerId = userService.getPandaOwner();
        assertEquals(expectedOwnerId, actualOwnerId);
    }

    @Test
    public void getPandaOwner_ReturnsNullWhenNoPandaOwner() {
        assertThrows(IllegalStateException.class, () -> userService.getUserById(1L));
    }

}
