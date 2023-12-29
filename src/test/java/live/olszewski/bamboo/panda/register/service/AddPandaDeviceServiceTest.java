package live.olszewski.bamboo.panda.register.service;

import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.panda.objects.PandaDao;
import live.olszewski.bamboo.panda.register.RegisterPandaService;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class AddPandaDeviceServiceTest {

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
    public void shouldAddPandaDeviceWhenValidRegisterPandaGiven() {
        testUtils.addUsersToDatabase(1);
        testUtils.setUserStorageByUserId(1L, true);
        registerPandaService.addPandaDevice(testUtils.generateRegisterPandaWithId(1L));
        PandaDao expectedPandaDao = testUtils.generatePandaDaoWithId(1L);
        PandaDao actualPandaDao = pandaRepository.findById(1L).get();
        assertEquals(1, pandaRepository.count());
        assertEquals(expectedPandaDao.getId(), actualPandaDao.getId());
        assertEquals(expectedPandaDao.getUuid(), actualPandaDao.getUuid());
        assertEquals(expectedPandaDao.getLocation(), actualPandaDao.getLocation());
        assertEquals(expectedPandaDao.getName(), actualPandaDao.getName());
        assertEquals(expectedPandaDao.getStatus(), actualPandaDao.getStatus());
        assertEquals(expectedPandaDao.getOwner(), actualPandaDao.getOwner());
        assertNotEquals(expectedPandaDao.getApi_key(), actualPandaDao.getApi_key());

    }

    @Test
    public void shouldNotAddPandaDeviceWhenNullRegisterPandaGiven() {
        testUtils.addUsersToDatabase(1);
        testUtils.setUserStorageByUserId(1L, true);
        assertThrows(NullPointerException.class, () -> registerPandaService.addPandaDevice(null));
    }
}
