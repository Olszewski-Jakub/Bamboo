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

/**
 * This class implements the UserService interface.
 * It provides methods for managing users, such as getting all users, adding a new user, deleting a user, and getting user details.
 * It uses the @Service annotation to indicate that it is a service class.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserStorage userStorage;

    /**
     * This method returns a list of all users.
     * It checks if the current user is an administrator before returning the list.
     * If the current user is not an administrator, it throws an AccessDeniedException.
     *
     * @return a list of all users
     */
    @Override
    public List<UserDto> getUsers() {
        if (!userStorage.isAdministrator())
            throw new AccessDeniedException("User is not administrator");
        return userRepository.findAll().stream().map(UserDao::toUserDto).toList();
    }

    /**
     * This method adds a new user.
     * It checks if a user with the same email already exists before adding the new user.
     * If a user with the same email already exists, it throws an IllegalStateException.
     *
     * @param registerUser the new user to add
     */
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

    /**
     * This method returns the details of the current user.
     *
     * @return the details of the current user
     */
    @Override
    public UserDto currentUserDetails() {
        return userRepository.findUserByEmail(userStorage.getCurrentUserEmail()).orElseThrow(() -> new IllegalStateException("User with email " + userStorage.getCurrentUserEmail() + " does not exists")).toUserDto();
    }

    /**
     * This method deletes a user by their id.
     * It checks if a user with the given id exists before deleting the user.
     * If a user with the given id does not exist, it throws an IllegalStateException.
     *
     * @param id the id of the user to delete
     */
    @Override
    public void deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("User with id " + id + " deos not exists");
        }
        userRepository.deleteById(id);
    }

    /**
     * This method returns the owner of a panda.
     *
     * @return the owner of a panda
     */
    @Override
    public Long getPandaOwner() {
        UserDao userDao = userRepository.findUserByEmail(userStorage.getCurrentUserEmail()).orElseThrow(() -> new IllegalStateException("User with email " + userStorage.getCurrentUserEmail() + " does not exists"));
        return userDao.getId();
    }

    /**
     * This method checks if a user is an administrator.
     *
     * @param email the email of the user
     * @return true if the user is an administrator, false otherwise
     */
    @Override
    public Boolean isAdministrator(String email) {
        UserDao userDao = userRepository.findUserByEmail(email).orElseThrow(() -> new IllegalStateException("User with email " + userStorage.getCurrentUserEmail() + " does not exists"));
        return userDao.getAdministrator();
    }

    /**
     * This method returns the id of a user.
     *
     * @param email the email of the user
     * @return the id of the user
     */
    @Override
    public Long getUserId(String email) {
        UserDao userDao = userRepository.findUserByEmail(email).orElseThrow(() -> new IllegalStateException("User with email " + userStorage.getCurrentUserEmail() + " does not exists"));
        return userDao.getId();
    }

    /**
     * This method returns the email of a user.
     *
     * @param id the id of the user
     * @return the email of the user
     */
    @Override
    public String getUserEmailById(Long id) {
        UserDao userDao = userRepository.findUserById(id).orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exists"));
        return userDao.getEmail();
    }

    /**
     * This method returns the details of a user.
     *
     * @param id the id of the user
     * @return the details of the user
     */
    @Override
    public UserDto getUserById(Long id) {
        UserDao userDao = userRepository.findUserById(id).orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exists"));
        return userDao.toUserDto();
    }

}
