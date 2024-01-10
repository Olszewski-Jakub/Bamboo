package live.olszewski.bamboo.user.service;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.testUtils.TestUtils;
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

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@SuppressWarnings("resource")
public class DeleteUserTest {

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
    public void deleteUser_DeletesUserFromRepository() {
        testUtils.addUsersToDatabase(1);
        Long id = 1L;
        userService.deleteUser(id);
        assertThrows(IllegalStateException.class, () -> userService.getUserById(id));

    }
    @Test
    public void deleteUser_ThrowsExceptionWhenUserDoesNotExist() {

        ResponseEntity<ApiResponseDto<?>> response = userService.deleteUser(100L);
        assertEquals(404, Objects.requireNonNull(response.getBody()).getStatusCode());
        assertEquals("User with id 100 does not exist", response.getBody().getMessage());
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
