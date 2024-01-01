package live.olszewski.bamboo.user;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
    private ApiResponseBuilder builder;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ResponseEntity<ApiResponseDto<?>> getUsers() {
        if (!userStorage.isAdministrator())
            return builder.error().code401("Access denied, user is not an administrator");
        return builder.success().code200("asd", userRepository.findAll().stream().map(UserDao::toUserDto).toList());
    }


    @Override
    public ResponseEntity<ApiResponseDto<?>> addNewUser(RegisterUser registerUser) {
        Optional<UserDao> userOptional = userRepository.findUserByEmail(registerUser.getEmail());
        if (userOptional.isPresent()) {
            return builder.error().code409("User with email " + registerUser.getEmail() + " already exists. Can not create user with this email");
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
            return ResponseEntity.status(500).body(builder.buildErrorResponse(500, "Internal server error" + e.getMessage()));
        }
        return ResponseEntity.ok(builder.buildSuccessResponse(200, "User added successfully", null));
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> currentUserDetails() {
        Optional<UserDao> userOptional = userRepository.findUserByEmail(userStorage.getCurrentUserEmail());
        return userOptional.<ResponseEntity<ApiResponseDto<?>>>map(userDao -> ResponseEntity.ok(builder.buildSuccessResponse(200, "Current details of user with id:" + userStorage.getCurrentUserId().toString(), userDao.toUserDto()))).orElseGet(() -> ResponseEntity.status(404).body(builder.buildErrorResponse(404, "User not found")));
    }


    @Override
    public ResponseEntity<ApiResponseDto<?>> deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            return ResponseEntity.status(404).body(builder.buildErrorResponse(404, "User with id " + id + " does not exists"));
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok(builder.buildSuccessResponse(200, "User with id" + id + "removed successfully", null));
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
    public String getUserEmailById(Long id) {
        UserDao userDao = userRepository.findUserById(id).orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exists"));
        return userDao.getEmail();
    }


    @Override
    public UserDto getUserById(Long id) {
        UserDao userDao = userRepository.findUserById(id).orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exists"));
        return userDao.toUserDto();
    }


    @Override
    public Boolean isValidUserById(Long id) {
        return userRepository.existsById(id);
    }

}
