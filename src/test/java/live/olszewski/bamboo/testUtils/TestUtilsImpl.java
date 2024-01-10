package live.olszewski.bamboo.testUtils;

import live.olszewski.bamboo.apiKeys.ApiKeysRepository;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.panda.objects.PandaDao;
import live.olszewski.bamboo.panda.register.RegisterPanda;
import live.olszewski.bamboo.services.uuid.UUIDService;
import live.olszewski.bamboo.user.UserDao;
import live.olszewski.bamboo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class provides utility methods for testing.
 * It includes methods for clearing databases, comparing objects, generating test data, and setting up user storage.
 */
@Service
public class TestUtilsImpl implements TestUtils {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PandaRepository pandaRepository;

    @Autowired
    private ApiKeysRepository apiKeysRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserStorage userStorage;

    @Autowired
    private UUIDService uuidService;

    @Override
    public void clearAllDatabases() {
        clearUserDatabase();
        clearPandaDatabase();
        clearApiKeysDatabase();
    }

    /**
     * Deletes all users from the database and resets the user sequence.
     */
    public void clearUserDatabase() {
        userRepository.deleteAll();
        jdbcTemplate.execute("ALTER SEQUENCE user_sequence RESTART WITH 1");
    }

    /**
     * Deletes all Panda devices from the database and resets the panda sequence.
     */
    @Override
    public void clearPandaDatabase() {
        pandaRepository.deleteAll();
        jdbcTemplate.execute("ALTER SEQUENCE panda_sequence RESTART WITH 1");
    }

    /**
     * Deletes all API keys from the database and resets the API keys sequence.
     */
    @Override
    public void clearApiKeysDatabase() {
        apiKeysRepository.deleteAll();
        jdbcTemplate.execute("ALTER SEQUENCE api_keys_sequence RESTART WITH 1");
    }

    /**
     * Clears the current user from the user storage.
     */
    @Override
    public void clearUserStorage() {
        userStorage.clearCurrentUser();
    }

    /**
     * Compares all fields of two objects for equality.
     *
     * @param obj1 the first object to compare
     * @param obj2 the second object to compare
     * @return true if all fields are equal, false otherwise
     */
    @Override
    public boolean areObjectEqual(Object obj1, Object obj2) {
        try {
            for (Field field : obj1.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value1 = field.get(obj1);
                Object value2 = field.get(obj2);
                if (value1 == null) {
                    if (value2 != null) return false;
                } else if (!value1.equals(value2)) return false;
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error comparing object fields", e);
        }
        return true;
    }

    /**
     * Generates a UserDao object with the given id.
     *
     * @param id the id of the user
     * @return a UserDao object with the given id
     */
    @Override
    public UserDao generateUserDaoWithId(Long id) {
        return new UserDao(id, "UUID" + id, "user" + id, "user" + id, "user" + id + "@test.com", (id + 2) % 3 == 0);
    }

    /**
     * Generates a given number of users and saves them to the database.
     * The id of the users starts from 1.
     *
     * @param numberOfUsers the number of users to generate
     */
    @Override
    public void addUsersToDatabase(int numberOfUsers) {
        List<UserDao> users = new ArrayList<>();
        for (int id = 1; id <= numberOfUsers; id++) {
            UserDao user = new UserDao("UUID" + id, "user" + id, "user" + id, "user" + id + "@test.com", (id + 2) % 3 == 0);
            users.add(user);
        }
        userRepository.saveAll(users);
    }

    /**
     * Sets the current user in the user storage to the user with the given id.
     *
     * @param id            the id of the user
     * @param administrator true if the user is an administrator, false otherwise
     */
    @Override
    public void setUserStorageByUserId(Long id, boolean administrator) {
        UserDao userDao = generateUserDaoWithId(id);
        userStorage.setCurrentUser(userDao.getName(), userDao.getEmail(), userDao.getUID(), administrator, userDao.getId());
    }

    /**
     * Generates a RegisterPanda object with the given id.
     *
     * @param id the id of the RegisterPanda
     * @return a RegisterPanda object with the given id
     */
    @Override
    public RegisterPanda generateRegisterPandaWithId(Long id) {
        return new RegisterPanda("location" + id, "name" + id);
    }

    /**
     * Generates a PandaDao object with the given id.
     *
     * @param id the id of the PandaDao
     * @return a PandaDao object with the given id
     */
    @Override
    public PandaDao generatePandaDaoWithId(Long id) {
        PandaDao pandaDao = new PandaDao(id, "", "location" + id, "name" + id, true, id, "api_key" + id);
        pandaDao.setUuid(uuidService.generateUUIDFromString(pandaDao.valuesForUuidGeneration()).toString());
        return pandaDao;
    }

    @Override
    public <T> List<T> mapToModelList(List<Object> objectList, Function<Object, T> mapper) {
        return objectList.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    @Override
    public ApiResponseDto<?> deserialize(ResponseEntity<ApiResponseDto<?>> responseEntity) {
        return responseEntity.getBody();
    }
}
