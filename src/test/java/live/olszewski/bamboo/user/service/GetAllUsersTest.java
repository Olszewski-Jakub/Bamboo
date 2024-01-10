package live.olszewski.bamboo.user.service;


import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.testUtils.TestUtils;
import live.olszewski.bamboo.user.UserDto;
import live.olszewski.bamboo.user.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@SuppressWarnings({"unchecked", "resource"})

public class GetAllUsersTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserStorage userStorage;

    @Autowired
    private TestUtils testUtils;


    @Container
    static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16").withDatabaseName("integration-tests-db").withUsername("postgres").withPassword("admin").withInitScript("init.sql");

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
    }


    @Test
    public void getAllUsers() {
        testUtils.addUsersToDatabase(3);
        userStorage.setCurrentUser("test1", "user1@test.com", "UUID1", true, 1L);

        ResponseEntity<ApiResponseDto<?>> response = userService.getUsers();

        assertEquals(200, requireNonNull(response.getBody()).getStatusCode());
        List<UserDto> usersList = new ArrayList<>();
        usersList.add(testUtils.generateUserDaoWithId(1L).toUserDto());
        usersList.add(testUtils.generateUserDaoWithId(2L).toUserDto());
        usersList.add(testUtils.generateUserDaoWithId(3L).toUserDto());
        List<UserDto> receivedUsersList = (List<UserDto>) requireNonNull(userService.getUsers().getBody()).getData();
        for (int i = 0; i < receivedUsersList.size(); i++) {
            assertTrue(testUtils.areObjectEqual(usersList.get(i), receivedUsersList.get(i)));
        }
    }

    @Test
    public void getUsers_ReturnsEmptyListWhenNoUsers() {
        List<UserDto> users = (List<UserDto>) requireNonNull(userService.getUsers().getBody()).getData();

        assertTrue(users.isEmpty());
    }

    @Test
    public void getUsers_ReturnsSingleUserWhenOneUserExists() {
        testUtils.addUsersToDatabase(1);
        List<UserDto> users = (List<UserDto>) requireNonNull(userService.getUsers().getBody()).getData();

        assertEquals(1, users.size());
    }

    @Test
    public void getUsers_ReturnsMultipleUsersWhenMultipleUsersExist() {
        testUtils.addUsersToDatabase(3);
        List<UserDto> users = (List<UserDto>) requireNonNull(userService.getUsers().getBody()).getData();

        assertEquals(3, users.size());
    }

    @Test
    public void getUsers_ReturnsCorrectUsers() {
        testUtils.addUsersToDatabase(2);
        List<UserDto> users = (List<UserDto>) requireNonNull(userService.getUsers().getBody()).getData();

        assertEquals(2, users.size());
        assertTrue(testUtils.areObjectEqual(users.get(0), testUtils.generateUserDaoWithId(1L).toUserDto()));
        assertTrue(testUtils.areObjectEqual(users.get(1), testUtils.generateUserDaoWithId(2L).toUserDto()));
    }
}
