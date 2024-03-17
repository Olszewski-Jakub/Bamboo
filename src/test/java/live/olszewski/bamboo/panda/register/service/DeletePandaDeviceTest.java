package live.olszewski.bamboo.panda.register.service;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.panda.register.RegisterPandaService;
import live.olszewski.bamboo.testUtils.TestUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@SuppressWarnings("resource")
public class DeletePandaDeviceTest {

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
    public void deletePandaDeviceReturnsSuccessWhenDeviceExists() {
        Long id = 1L;
        ApiResponseDto<?> apiResponseDto = new ApiResponseDto<>();
        when(registerPandaService.deletePandaDevice(id)).thenReturn(new ResponseEntity<>(apiResponseDto, HttpStatus.OK));

        ResponseEntity<ApiResponseDto<?>> response = registerPandaService.deletePandaDevice(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deletePandaDeviceReturnsNotFoundWhenDeviceDoesNotExist() {
        Long id = 2L;
        ApiResponseDto<?> apiResponseDto = new ApiResponseDto<>();
        when(registerPandaService.deletePandaDevice(id)).thenReturn(new ResponseEntity<>(apiResponseDto, HttpStatus.NOT_FOUND));

        ResponseEntity<ApiResponseDto<?>> response = registerPandaService.deletePandaDevice(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
