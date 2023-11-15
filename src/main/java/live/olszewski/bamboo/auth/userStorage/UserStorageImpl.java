package live.olszewski.bamboo.auth.userStorage;

import org.springframework.stereotype.Component;

@Component
public class UserStorageImpl implements UserStorage {

    private String currentUserName;
    private String currentUserEmail;
    private String currentUserUuid;

    private Boolean isAdministrator;

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
    public void setCurrentUser(String name, String email, String uuid, Boolean isAdministrator) {
        this.currentUserName = name;
        this.currentUserEmail = email;
        this.currentUserUuid = uuid;
        this.isAdministrator = isAdministrator;
    }
}
