package live.olszewski.bamboo.panda.register.service;

import live.olszewski.bamboo.BaeldungPostgresqlContainer;
import live.olszewski.bamboo.panda.PandaDao;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.panda.register.RegisterPandaService;
import live.olszewski.bamboo.testUtils.TestUtils;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
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


    @ClassRule
    public static PostgreSQLContainer<BaeldungPostgresqlContainer> postgreSQLContainer = BaeldungPostgresqlContainer.getInstance();

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
