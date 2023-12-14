package live.olszewski.bamboo.user;

import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.testUtils.TestUtils;
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
