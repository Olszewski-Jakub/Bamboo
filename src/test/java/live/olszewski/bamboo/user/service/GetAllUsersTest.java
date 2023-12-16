package live.olszewski.bamboo.user.service;

import live.olszewski.bamboo.BaeldungPostgresqlContainer;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.testUtils.TestUtils;
import live.olszewski.bamboo.user.UserDto;
import live.olszewski.bamboo.user.UserService;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class GetAllUsersTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserStorage userStorage;

    @Autowired
    private TestUtils testUtils;


    @ClassRule
    public static PostgreSQLContainer<BaeldungPostgresqlContainer> postgreSQLContainer = BaeldungPostgresqlContainer.getInstance();

    @BeforeEach
    public void clearDatabase() {
        testUtils.clearUserDatabase();
    }


    @Test
    public void getAllUsers() throws Exception {
        testUtils.addUsersToDatabase(3);
        userStorage.setCurrentUser("test1", "user1@test.com", "UUID1", true, 1L);
        assertEquals(3, userService.getUsers().size());
    }

    @Test
    public void getUsers_ReturnsEmptyListWhenNoUsers() {
        assertTrue(userService.getUsers().isEmpty());
    }

    @Test
    public void getUsers_ReturnsSingleUserWhenOneUserExists() {
        testUtils.addUsersToDatabase(1);
        assertEquals(1, userService.getUsers().size());
    }

    @Test
    public void getUsers_ReturnsMultipleUsersWhenMultipleUsersExist() {
        testUtils.addUsersToDatabase(3);
        assertEquals(3, userService.getUsers().size());
    }

    @Test
    public void getUsers_ReturnsCorrectUsers() {
        testUtils.addUsersToDatabase(2);
        ArrayList<UserDto> users = new ArrayList<>(userService.getUsers());
        assertEquals(2, users.size());
        assertTrue(testUtils.areObjectEqual(users.get(0), testUtils.generateUserDaoWithId(1L).toUserDto()));
        assertTrue(testUtils.areObjectEqual(users.get(1), testUtils.generateUserDaoWithId(2L).toUserDto()));
    }
}
