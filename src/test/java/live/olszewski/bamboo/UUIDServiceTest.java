package live.olszewski.bamboo;

import com.google.firebase.FirebaseApp;
import live.olszewski.bamboo.auth.FirebaseConfig;
import live.olszewski.bamboo.services.uuid.UUIDService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class UUIDServiceTest {

    @Autowired
    UUIDService uuidService;
    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
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

    @Test
    public void generateUUIDFromStringReturnsSameUUIDForSameInput() {
        String input = "testInput";
        UUID uuid1 = uuidService.generateUUIDFromString(input);
        UUID uuid2 = uuidService.generateUUIDFromString(input);
        assertEquals(uuid1, uuid2);
    }

    @Test
    public void generateUUIDFromStringReturnsDifferentUUIDForDifferentInput() {
        String input1 = "testInput1";
        String input2 = "testInput2";
        UUID uuid1 = uuidService.generateUUIDFromString(input1);
        UUID uuid2 = uuidService.generateUUIDFromString(input2);
        assertNotEquals(uuid1, uuid2);
    }

}
