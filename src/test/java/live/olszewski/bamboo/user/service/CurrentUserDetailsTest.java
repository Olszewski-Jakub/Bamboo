package live.olszewski.bamboo.user.service;

import live.olszewski.bamboo.auth.userStorage.UserStorage;
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
public class CurrentUserDetailsTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserStorage userStorage;

    @Autowired
    private TestUtils testUtils;


    //@ClassRule
    //  public static PostgreSQLContainer<BaeldungPostgresqlContainer> postgreSQLContainer = BaeldungPostgresqlContainer.getInstance();

    @BeforeEach
    public void clearDatabase() {
        testUtils.clearUserDatabase();
    }

    @Test
    public void currentUserDetails_ReturnsCorrectUserWhenExists() {
        UserDao userDao = testUtils.generateUserDaoWithId(1L);
        userStorage.setCurrentUser(userDao.getName(), userDao.getEmail(), userDao.getUID(), userDao.getAdministrator(), userDao.getId());
        testUtils.addUsersToDatabase(1);
        UserDto actualUser = userService.currentUserDetails();
        assertTrue(testUtils.areObjectEqual(userDao.toUserDto(), actualUser));
    }

    @Test
    public void currentUserDetails_ThrowsExceptionWhenUserDoesNotExist() {
        assertThrows(IllegalStateException.class, () -> userService.currentUserDetails());
    }
}
