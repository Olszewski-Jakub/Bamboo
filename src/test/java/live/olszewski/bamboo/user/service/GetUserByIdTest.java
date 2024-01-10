package live.olszewski.bamboo.user.service;

import live.olszewski.bamboo.testUtils.TestUtils;
import live.olszewski.bamboo.user.UserDao;
import live.olszewski.bamboo.user.UserDto;
import live.olszewski.bamboo.user.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@SuppressWarnings("resource")
public class GetUserByIdTest {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private UserService userService;

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
