package live.olszewski.bamboo.auth.userStorage;

import live.olszewski.bamboo.user.UserDao;
import org.springframework.stereotype.Component;

@Component
public class UserStorageImpl implements UserStorage {

    private String currentUserName;

    private String currentUserSurname;
    private String currentUserEmail;
    private String currentUserUuid;

    private Boolean isAdministrator;

    private Long id;

    @Override
    public String getCurrentUserName() {
        return currentUserName;
    }

    @Override
    public String getCurrentUserEmail() {
        return currentUserEmail;
    }

    @Override
    public String getCurrentUserUuid() {
        return currentUserUuid;
    }

    @Override
    public Boolean isAdministrator() {
        return isAdministrator;
    }

    @Override
    public Long getCurrentUserId() {
        return id;
    }

    @Override
    public void setCurrentUser(String name, String surname, String email, String uuid, Boolean isAdministrator, Long id) {
        this.currentUserName = name;
        this.currentUserSurname = surname;
        this.currentUserEmail = email;
        this.currentUserUuid = uuid;
        this.isAdministrator = isAdministrator;
        this.id = id;
    }

    @Override
    public void clearCurrentUser() {
        this.currentUserName = null;
        this.currentUserEmail = null;
        this.currentUserUuid = null;
        this.isAdministrator = null;
        this.id = null;
    }

    @Override
    public UserDao getCurrentUser() {
        return new UserDao(id, currentUserUuid, currentUserName, currentUserSurname, currentUserEmail, isAdministrator);
    }
}
