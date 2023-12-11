package live.olszewski.bamboo.user;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<UserDto> getUsers();
    void addNewUser(RegisterUser registerUser);

    UserDto currentUserDetails();

    void deleteUser(Long id);

    Long getPandaOwner();

    Boolean isAdministrator(String email);

    Long getUserId(String email);
    String getUserById(Long id);


}
