package live.olszewski.bamboo.auth.userStorage;

public interface UserStorage {
    String getCurrentUserName();
    String getCurrentUserEmail();
    String getCurrentUserUuid();

    void setCurrentUser(String name, String email, String uuid);
}
