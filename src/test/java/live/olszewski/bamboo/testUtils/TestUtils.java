package live.olszewski.bamboo.testUtils;

import live.olszewski.bamboo.user.UserDao;

public interface TestUtils {

    void clearUserDatabase();

    boolean areObjectEqual(Object obj1, Object obj2);
    UserDao generateUserDaoWithId(Long id);
    void addUsersToDatabase(int numberOfUsers);
}