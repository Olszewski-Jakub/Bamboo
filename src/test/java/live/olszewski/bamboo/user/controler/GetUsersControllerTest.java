package live.olszewski.bamboo.user.controler;


import live.olszewski.bamboo.BaeldungPostgresqlContainer;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.testUtils.TestUtils;
import live.olszewski.bamboo.user.UserDao;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Testcontainers
public class GetUsersControllerTest {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserStorage userStorage;
    @ClassRule
    public static PostgreSQLContainer<BaeldungPostgresqlContainer> postgreSQLContainer = BaeldungPostgresqlContainer.getInstance();
    @BeforeEach
    public void clearDatabase() {
        testUtils.clearUserDatabase();
    }

    @Test
    public void testGetUser() throws Exception {
        testUtils.addUsersToDatabase(1);
        UserDao userDao = testUtils.generateUserDaoWithId(1L);
        userStorage.setCurrentUser(userDao.getName(), userDao.getEmail(), userDao.getUID(), userDao.getAdministrator(), userDao.getId());
        String expectedJson = "[{"
                + "\"id\":" + userDao.getId() + ","
                + "\"name\":\"" + userDao.getName() + "\","
                + "\"surname\":\"" + userDao.getSurname() + "\","
                + "\"email\":\"" + userDao.getEmail() + "\","
                + "\"uid\":\"" + userDao.getUID()+ "\","
                + "\"administrator\":" + userDao.getAdministrator()
                + "}]";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user"))
                .andExpect(status().isOk()).andExpect(content().json(expectedJson));
    }

    @Test
    public void getUser_whenUserExists_returnsCorrectUser() throws Exception {
        testUtils.addUsersToDatabase(1);
        UserDao userDao = testUtils.generateUserDaoWithId(1L);
        userStorage.setCurrentUser(userDao.getName(), userDao.getEmail(), userDao.getUID(), userDao.getAdministrator(), userDao.getId());
        String expectedJson = "[{"
                + "\"id\":" + userDao.getId() + ","
                + "\"name\":\"" + userDao.getName() + "\","
                + "\"surname\":\"" + userDao.getSurname() + "\","
                + "\"email\":\"" + userDao.getEmail() + "\","
                + "\"uid\":\"" + userDao.getUID()+ "\","
                + "\"administrator\":" + userDao.getAdministrator()
                + "}]";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user"))
                .andExpect(status().isOk()).andExpect(content().json(expectedJson));
    }

    @Test
    public void getUser_whenUserDoesNotExist_returnsNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user"))
                .andExpect(status().isOk()).andExpect(content().json("[]"));
    }

    @Test
    public void getUser_whenMultipleUsersExist_returnsCorrectUser() throws Exception {
        testUtils.addUsersToDatabase(2);
        UserDao userDao1 = testUtils.generateUserDaoWithId(1L);
        UserDao userDao2 = testUtils.generateUserDaoWithId(2L);
        userStorage.setCurrentUser(userDao1.getName(), userDao1.getEmail(), userDao1.getUID(), userDao1.getAdministrator(), userDao1.getId());
        String expectedJson = "[{"
                + "\"id\":" + userDao1.getId() + ","
                + "\"name\":\"" + userDao1.getName() + "\","
                + "\"surname\":\"" + userDao1.getSurname() + "\","
                + "\"email\":\"" + userDao1.getEmail() + "\","
                + "\"uid\":\"" + userDao1.getUID()+ "\","
                + "\"administrator\":" + userDao1.getAdministrator()
                + "}," + "{"
                + "\"id\":" + userDao2.getId() + ","
                + "\"name\":\"" + userDao2.getName() + "\","
                + "\"surname\":\"" + userDao2.getSurname() + "\","
                + "\"email\":\"" + userDao2.getEmail() + "\","
                + "\"uid\":\"" + userDao2.getUID()+ "\","
                + "\"administrator\":" + userDao2.getAdministrator()
                +"}]";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user"))
                .andExpect(status().isOk()).andExpect(content().json(expectedJson));
    }
}
