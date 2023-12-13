package live.olszewski.bamboo;

import com.google.firebase.FirebaseApp;
import live.olszewski.bamboo.auth.FirebaseConfig;
import live.olszewski.bamboo.services.apiKey.ApiKeyService;
import live.olszewski.bamboo.services.apiKey.ApiKeyServiceImpl;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
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
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class ApiKeyServiceTest {

    @Autowired
    ApiKeyService apiKeyService;

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
    public void generatesApiKeyOfCorrectLength() {
        String seed = "testSeed";
        String apiKey = apiKeyService.generateApiKey(seed);
        assertEquals(ApiKeyServiceImpl.DEFAULT_API_KEY_LENGTH, apiKey.length());
    }

    @Test
    public void generatesDifferentApiKeysForDifferentSeeds() {
        String seed1 = "testSeed1";
        String seed2 = "testSeed2";
        String apiKey1 = apiKeyService.generateApiKey(seed1);
        String apiKey2 = apiKeyService.generateApiKey(seed2);
        assertNotEquals(apiKey1, apiKey2);
    }

    @Test
    public void generatesSameApiKeyForSameSeed() {
        String seed = "testSeed";
        String apiKey1 = apiKeyService.generateApiKey(seed);
        String apiKey2 = apiKeyService.generateApiKey(seed);
        assertEquals(apiKey1, apiKey2);
    }

}
