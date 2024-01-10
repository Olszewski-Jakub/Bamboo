package live.olszewski.bamboo.user.service;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.testUtils.TestUtils;
import live.olszewski.bamboo.user.UserDao;
import live.olszewski.bamboo.user.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Container
    static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("integration-tests-db")
            .withUsername("postgres")
            .withPassword("admin")
            .withInitScript("init.sql");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeAll
    public static void setUp() {
        postgres.start();
    }

    @AfterAll
    public static void tearDown() {
        postgres.stop();
    }

    @BeforeEach
    public void clearDatabase() {
        testUtils.clearUserDatabase();
    }

    @Test
    public void currentUserDetails_ReturnsCorrectUserWhenExists() {
        UserDao userDao = testUtils.generateUserDaoWithId(1L);
        userStorage.setCurrentUser(userDao.getName(), userDao.getEmail(), userDao.getUID(), userDao.getAdministrator(), userDao.getId());
        testUtils.addUsersToDatabase(1);
        ResponseEntity<ApiResponseDto<?>> actualUser = userService.currentUserDetails();
        assertTrue(testUtils.areObjectEqual(userDao.toUserDto(), actualUser.getBody().getData()));
    }

    @Test
    public void currentUserDetails_ThrowsExceptionWhenUserDoesNotExist() {
        ResponseEntity<ApiResponseDto<?>> reponse = userService.currentUserDetails();
        assertEquals(404, reponse.getBody().getStatusCode());
        assertEquals("User with email user1@test.com does not exist", reponse.getBody().getMessage());
    }
}
