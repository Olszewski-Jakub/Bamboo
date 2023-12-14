package live.olszewski.bamboo.user;

import java.util.List;


public interface UserService {

    List<UserDto> getUsers();
    void addNewUser(RegisterUser registerUser);

    UserDto currentUserDetails();

    void deleteUser(Long id);

    Long getPandaOwner();

    Boolean isAdministrator(String email);

    Long getUserId(String email);
    String getUserEmailById(Long id);

    UserDto getUserById(Long id);

}
