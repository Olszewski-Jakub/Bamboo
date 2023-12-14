package live.olszewski.bamboo.testUtils;

import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.user.UserDao;
import live.olszewski.bamboo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestUtilsImpl implements TestUtils {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserStorage userStorage;

    /**
     * Deletes all users from database
     */
    public void clearUserDatabase() {
        userRepository.deleteAll();
        jdbcTemplate.execute("ALTER SEQUENCE user_sequence RESTART WITH 1");
    }

    /**
     * Compares fields of two objects
     * @param obj1 first object to compare
     * @param obj2 second object to compare
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
     * Generates UserDao with given id
     * @param id id of user
     * @return UserDao with given id
     */
    @Override
    public UserDao generateUserDaoWithId(Long id) {
        return new UserDao(id, "UUID" + id,"user" + id ,"user" + id,  "user" + id + "@test.com", (id+2) % 3 == 0);
    }

    /**
     * Generates given number of users and saves them to database
     * id of users starts from 1
     * @param numberOfUsers number of users to generate
     */
    @Override
    public void addUsersToDatabase(int numberOfUsers) {
        List<UserDao> users = new ArrayList<>();
        for (int id = 1; id <= numberOfUsers; id++) {
            UserDao user = new UserDao( "UUID" + id,"user" + id ,"user" + id,  "user" + id + "@test.com", (id+2) % 3 == 0);
            users.add(user);
        }
        userRepository.saveAll(users);
    }

    /**
     * Sets current user in userStorage to user with given id
     * @param id id of user
     * @param administrator true if user is administrator, false otherwise
     */
    @Override
    public void setUserStorageByUserId(Long id, boolean administrator) {
        UserDao userDao = generateUserDaoWithId(id);
        userStorage.setCurrentUser(userDao.getName(), userDao.getEmail(), userDao.getUID(), administrator, userDao.getId());
    }


}
