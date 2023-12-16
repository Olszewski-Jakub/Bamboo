package live.olszewski.bamboo.auth.userStorage;

public interface UserStorage {
    String getCurrentUserName();
    String getCurrentUserEmail();
    String getCurrentUserUuid();

    Long getCurrentUserId();

    Boolean isAdministrator();

    void setCurrentUser(String name, String email, String uuid, Boolean isAdministrator, Long id);

    void clearCurrentUser();
}
