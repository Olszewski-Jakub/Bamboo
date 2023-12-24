package live.olszewski.bamboo.user;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserStorage userStorage;
    @Autowired
    private ApiResponseBuilder apiResponseBuilder;


    @Override
    public ResponseEntity<ApiResponseDto<?>> getUsers() {
        if (!userStorage.isAdministrator())
            return ResponseEntity.status(403).body(apiResponseBuilder.buildErrorResponse(403, "Unauthorized"));
        return ResponseEntity.ok(apiResponseBuilder.buildSuccessResponse(200, "Success", userRepository.findAll().stream().map(UserDao::toUserDto).toList()));
    }


    @Override
    public ResponseEntity<ApiResponseDto<?>> addNewUser(RegisterUser registerUser) {
        Optional<UserDao> userOptional = userRepository.findUserByEmail(registerUser.getEmail());
        if (userOptional.isPresent()) {
            return ResponseEntity.status(409).body(apiResponseBuilder.buildErrorResponse(409, "User already exists"));
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
            return ResponseEntity.status(500).body(apiResponseBuilder.buildErrorResponse(500, "Internal server error" + e.getMessage()));
        }
        return ResponseEntity.ok(apiResponseBuilder.buildSuccessResponse(200, "User added successfully", null));
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> currentUserDetails() {
        Optional<UserDao> userOptional = userRepository.findUserByEmail(userStorage.getCurrentUserEmail());
        return userOptional.<ResponseEntity<ApiResponseDto<?>>>map(userDao -> ResponseEntity.ok(apiResponseBuilder.buildSuccessResponse(200, "Current details of user with id:" + userStorage.getCurrentUserId().toString(), userDao.toUserDto()))).orElseGet(() -> ResponseEntity.status(404).body(apiResponseBuilder.buildErrorResponse(404, "User not found")));
    }


    @Override
    public ResponseEntity<ApiResponseDto<?>> deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            return ResponseEntity.status(404).body(apiResponseBuilder.buildErrorResponse(404, "User with id " + id + " does not exists"));
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok(apiResponseBuilder.buildSuccessResponse(200, "User with id" + id + "removed successfully", null));
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
