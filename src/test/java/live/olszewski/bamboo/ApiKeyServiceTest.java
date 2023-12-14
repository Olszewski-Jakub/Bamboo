package live.olszewski.bamboo;

import live.olszewski.bamboo.services.apiKey.ApiKeyService;
import live.olszewski.bamboo.services.apiKey.ApiKeyServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class ApiKeyServiceTest {

    @Autowired
    ApiKeyService apiKeyService;

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
