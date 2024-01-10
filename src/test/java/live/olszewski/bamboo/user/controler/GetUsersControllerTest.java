package live.olszewski.bamboo.user.controler;


import com.google.firebase.auth.FirebaseAuthException;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.testUtils.TestUtils;
import live.olszewski.bamboo.user.UserDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Testcontainers
@SuppressWarnings("resource")
public class GetUsersControllerTest {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserStorage userStorage;
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
    public static void setUp() throws FirebaseAuthException {
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
    public void getUser_whenUserExists_returnsCorrectUser() throws Exception {
        testUtils.addUsersToDatabase(1);
        UserDao userDao = testUtils.generateUserDaoWithId(1L);
        userStorage.setCurrentUser(userDao.getName(), userDao.getEmail(), userDao.getUID(), userDao.getAdministrator(), userDao.getId());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(userDao.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value(userDao.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].surname").value(userDao.getSurname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].email").value(userDao.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].uid").value(userDao.getUID()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].administrator").value(userDao.getAdministrator()));
    }

    @Test
    public void getUser_whenUserDoesNotExist_returnsNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user"))
                .andExpect(status().isOk()).andExpect((MockMvcResultMatchers.jsonPath("$.data").isEmpty()));
    }

    @Test
    public void getUser_whenMultipleUsersExist_returnsCorrectUser() throws Exception {
        testUtils.addUsersToDatabase(2);
        UserDao userDao1 = testUtils.generateUserDaoWithId(1L);
        UserDao userDao2 = testUtils.generateUserDaoWithId(2L);
        userStorage.setCurrentUser(userDao1.getName(), userDao1.getEmail(), userDao1.getUID(), userDao1.getAdministrator(), userDao1.getId());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(userDao1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value(userDao1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].surname").value(userDao1.getSurname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].email").value(userDao1.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].uid").value(userDao1.getUID()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].administrator").value(userDao1.getAdministrator()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].id").value(userDao2.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].name").value(userDao2.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].surname").value(userDao2.getSurname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].email").value(userDao2.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].uid").value(userDao2.getUID()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].administrator").value(userDao2.getAdministrator()));
    }
}
