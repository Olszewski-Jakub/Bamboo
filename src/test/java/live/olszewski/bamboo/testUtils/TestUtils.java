package live.olszewski.bamboo.testUtils;

import live.olszewski.bamboo.panda.PandaDao;
import live.olszewski.bamboo.panda.register.RegisterPanda;
import live.olszewski.bamboo.user.UserDao;

public interface TestUtils {

    void clearAllDatabases();
    void clearUserDatabase();

    void clearPandaDatabase();
    void clearApiKeysDatabase();
    void clearUserStorage();
    boolean areObjectEqual(Object obj1, Object obj2);
    UserDao generateUserDaoWithId(Long id);
    void addUsersToDatabase(int numberOfUsers);

    void setUserStorageByUserId(Long id, boolean administrator);

    RegisterPanda generateRegisterPandaWithId(Long id);

    PandaDao generatePandaDaoWithId(Long id);
}