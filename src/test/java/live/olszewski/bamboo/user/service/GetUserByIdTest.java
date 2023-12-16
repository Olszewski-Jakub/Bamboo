package live.olszewski.bamboo.user.service;

import live.olszewski.bamboo.testUtils.TestUtils;
import live.olszewski.bamboo.user.UserDao;
import live.olszewski.bamboo.user.UserDto;
import live.olszewski.bamboo.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class GetUserByIdTest {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private UserService userService;

    //@ClassRule
    //  public static PostgreSQLContainer<BaeldungPostgresqlContainer> postgreSQLContainer = BaeldungPostgresqlContainer.getInstance();

    @BeforeEach
    public void clearDatabase() {
        testUtils.clearUserDatabase();
    }

    @Test
    public void getUserById_ReturnsCorrectUserWhenUserExists() {
        UserDao userDao = testUtils.generateUserDaoWithId(1L);
        testUtils.addUsersToDatabase(1);
        UserDto actualUser = userService.getUserById(1L);
        assertTrue(testUtils.areObjectEqual(userDao.toUserDto(), actualUser));
    }

    @Test
    public void getUserById_ThrowsExceptionWhenUserDoesNotExist() {
        assertThrows(IllegalStateException.class, () -> userService.getUserById(1L));
    }

}
