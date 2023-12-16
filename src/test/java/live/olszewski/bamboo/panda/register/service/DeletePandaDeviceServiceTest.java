package live.olszewski.bamboo.panda.register.service;

import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.panda.register.RegisterPanda;
import live.olszewski.bamboo.panda.register.RegisterPandaService;
import live.olszewski.bamboo.testUtils.TestUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
public class DeletePandaDeviceServiceTest {

    @Autowired
    private RegisterPandaService registerPandaService;

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private PandaRepository pandaRepository;
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
        testUtils.clearPandaDatabase();
    }

    @Test
    public void shouldDeletePandaDeviceWhenValidRegisterPandaGiven() {
        testUtils.addUsersToDatabase(1);
        pandaRepository.save(testUtils.generatePandaDaoWithId(1L));
        assertTrue(pandaRepository.findAll().size() == 1);
        registerPandaService.deletePandaDevice(1L);
        assertTrue(pandaRepository.findAll().isEmpty());
    }

    @Test
    public void shouldNotDeletePandaDeviceWhenNullRegisterPandaGiven() {
        assertThrows(IllegalArgumentException.class, () -> registerPandaService.deletePandaDevice(null));
    }
}