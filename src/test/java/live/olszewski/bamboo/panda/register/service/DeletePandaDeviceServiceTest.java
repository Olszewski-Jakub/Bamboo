package live.olszewski.bamboo.panda.register.service;

import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.panda.register.RegisterPandaService;
import live.olszewski.bamboo.testUtils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
    //@ClassRule
    //  public static PostgreSQLContainer<BaeldungPostgresqlContainer> postgreSQLContainer = BaeldungPostgresqlContainer.getInstance();

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
        assertThrows(InvalidDataAccessApiUsageException.class, () -> registerPandaService.deletePandaDevice(null));
    }
}