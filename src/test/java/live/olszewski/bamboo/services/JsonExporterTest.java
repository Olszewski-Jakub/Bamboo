package live.olszewski.bamboo.services;

import live.olszewski.bamboo.panda.config.PandaConfigDto;
import live.olszewski.bamboo.services.jsonExporter.JsonExporterService;
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

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@SuppressWarnings("resource")
public class JsonExporterTest {


    @Autowired
    JsonExporterService jsonExporterService;
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
    public void exportReturnsCorrectJsonForString() {
        String input = "test";
        String expectedJson = "\"test\"";
        String actualJson = jsonExporterService.export(input);
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void exportReturnsCorrectJsonForInteger() {
        Integer input = 123;
        String expectedJson = "123";
        String actualJson = jsonExporterService.export(input);
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void exportReturnsCorrectJsonForBoolean() {
        Boolean input = true;
        String expectedJson = "true";
        String actualJson = jsonExporterService.export(input);
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void exportReturnsCorrectJsonForPandaConfigDto() {
        PandaConfigDto input = new PandaConfigDto("uuid", "location", "name", "owner", "api_key");
        String expectedJson = "{\"uuid\":\"uuid\",\"location\":\"location\",\"name\":\"name\",\"owner\":\"owner\",\"api_key\":\"api_key\"}";
        String actualJson = jsonExporterService.export(input);
        assertEquals(expectedJson, actualJson);
    }
}
