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

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserStorage userStorage;

    @Override
    public List<UserDto> getUsers() {
        if (!userStorage.isAdministrator())
            throw new AccessDeniedException("User is not administrator");
        return userRepository.findAll().stream().map(UserDao::toUserDto).toList();
    }

    @Override
    public void addNewUser(RegisterUser registerUser) {
        Optional<UserDao> userOptional = userRepository.findUserByEmail(registerUser.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("User already exists");
        }
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(registerUser.getEmail())
                .setEmailVerified(false)
                .setPassword(registerUser.getPassword())
                .setDisplayName(registerUser.getName() + " " + registerUser.getSurname());

        try {
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            UserDao userDao = new UserDao(userRecord.getUid(), registerUser.getName(), registerUser.getSurname(), registerUser.getEmail());
            userRepository.save(userDao);
            System.out.println(userDao);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto currentUserDetails() {
        return userRepository.findUserByEmail(userStorage.getCurrentUserEmail()).orElseThrow(() -> new IllegalStateException("User with email " + userStorage.getCurrentUserEmail() + " does not exists")).toUserDto();
    }

    @Override
    public void deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("User with id " + id + " deos not exists");
        }
        userRepository.deleteById(id);
    }

    @Override
    public Long getPandaOwner() {
        UserDao userDao = userRepository.findUserByEmail(userStorage.getCurrentUserEmail()).orElseThrow(() -> new IllegalStateException("User with email " + userStorage.getCurrentUserEmail() + " does not exists"));
        return userDao.getId();
    }

    @Override
    public Boolean isAdministrator(String email) {
        UserDao userDao = userRepository.findUserByEmail(email).orElseThrow(() -> new IllegalStateException("User with email " + userStorage.getCurrentUserEmail() + " does not exists"));
        return userDao.getAdministrator();
    }

    @Override
    public Long getUserId(String email) {
        UserDao userDao = userRepository.findUserByEmail(email).orElseThrow(() -> new IllegalStateException("User with email " + userStorage.getCurrentUserEmail() + " does not exists"));
        return userDao.getId();
    }

    @Override
    public String getUserById(Long id) {
        UserDao userDao = userRepository.findUserById(id).orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exists"));
        return userDao.getEmail();
    }

}
