package live.olszewski.bamboo.user.service;

import live.olszewski.bamboo.BaeldungPostgresqlContainer;
import live.olszewski.bamboo.testUtils.TestUtils;
import live.olszewski.bamboo.user.UserDao;
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
public class GetUserEmailByIdTest {
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
    public void getUserEmailById_ReturnsCorrectEmailWhenUserExists() {
        UserDao userDao = testUtils.generateUserDaoWithId(1L);
        testUtils.addUsersToDatabase(1);
        String actualEmail = userService.getUserEmailById(1L);
        assertEquals(userDao.getEmail(), actualEmail);
    }

    @Test
    public void getUserEmailById_ThrowsExceptionWhenUserDoesNotExist() {
        assertThrows(IllegalStateException.class, () -> userService.getUserEmailById(1L));
    }
}
