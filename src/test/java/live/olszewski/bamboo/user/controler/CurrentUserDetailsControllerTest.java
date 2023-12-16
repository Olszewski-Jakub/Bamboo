package live.olszewski.bamboo.user.controler;

import live.olszewski.bamboo.testUtils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Testcontainers
public class CurrentUserDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestUtils testUtils;

    //@ClassRule
    //  public static PostgreSQLContainer<BaeldungPostgresqlContainer> postgreSQLContainer = BaeldungPostgresqlContainer.getInstance();

    @BeforeEach
    public void clearDatabase() {
        testUtils.clearUserDatabase();
    }


    @Test
    public void currentUserDetails_whenUserExists_returnsUserDetails() throws Exception {
        testUtils.setUserStorageByUserId(1L, true);
        testUtils.addUsersToDatabase(1);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/current"))
                .andExpect(status().isOk());
    }

}
