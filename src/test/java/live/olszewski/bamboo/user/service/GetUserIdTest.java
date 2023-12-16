package live.olszewski.bamboo.user.service;

import live.olszewski.bamboo.testUtils.TestUtils;
import live.olszewski.bamboo.user.UserDao;
import live.olszewski.bamboo.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class GetUserIdTest {
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
    public void getUserId_ReturnsCorrectIdWhenUserExists() {
        testUtils.addUsersToDatabase(1);
        UserDao user = testUtils.generateUserDaoWithId(1L);
        Long actualId = userService.getUserId(user.getEmail());
        assertEquals(user.getId(), actualId);
    }

    @Test
    public void getUserId_ThrowsExceptionWhenUserDoesNotExist() {
        String nonExistentEmail = "nonexistent@test.com";
        assertThrows(IllegalStateException.class, () -> userService.getUserId(nonExistentEmail));
    }
}
