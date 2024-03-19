package live.olszewski.bamboo.user;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.apiResponse.MessageService;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserStorage userStorage;
    private final ApiResponseBuilder builder;
    private final MessageService messageService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserStorage userStorage, ApiResponseBuilder builder, MessageService messageService) {
        this.userRepository = userRepository;
        this.userStorage = userStorage;
        this.builder = builder;
        this.messageService = messageService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getUsers() {
        if (!userHasAdminPrivileges())
            return builder.error().code401(messageService.getMessage("user.not.administrator"));
        return builder.success().code200("asd", retrieveAllUsersFromDatabase());
    }


    @Override
    public ResponseEntity<ApiResponseDto<?>> addNewUser(RegisterUser registerUser) {
        if (doesUserExist(registerUser.getEmail())) {
            return builder.error().code409(messageService.getMessage("user.email.exists", registerUser.getEmail()));
        }
        UserRecord.CreateRequest request = createNewUserRequest(registerUser);
        try {
            createNewUserInAuthProviderService(registerUser, request);
        } catch (FirebaseAuthException e) {
            builder.error().code500(messageService.getMessage("internal.server.error", e.getMessage()));
        }
        return builder.success().code200(messageService.getMessage("user.created", registerUser.getEmail()), null);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> currentUserDetails() {
        Optional<UserDao> userOptional = retrieveUserOptionalFromDatabase(getUserEmailFromStorage());
        if (userOptional.isEmpty()) {
            return builder.error().code404(messageService.getMessage("user.not.found", getUserEmailFromStorage()));
        }
        return builder.success().code200(messageService.getMessage("user.details", getUserEmailFromStorage()), userOptional.get().toUserDto());
    }


    @Override
    public ResponseEntity<ApiResponseDto<?>> deleteUser(Long id) {
        return Optional.of(doesUserExist(id)).filter(Boolean::booleanValue).map(exists -> {
            deleteUserFromDatabase(id);
            return builder.success().code200(messageService.getMessage("user.removed", id), null);
        }).orElseGet(() -> builder.error().code404(messageService.getMessage("user.not.found.id", id)));
    }


    @Override
    public Long getPandaOwner() {
        return retrieveUserDtoFromDatabase(getUserEmailFromStorage()).getId();
    }


    @Override
    public Boolean isAdministrator(String email) {
        return retrieveUserDtoFromDatabase(email).getAdministrator();
    }


    @Override
    public Long getUserId(String email) {
        return retrieveUserDtoFromDatabase(email).getId();
    }


    @Override
    public String getUserEmailById(Long id) {
        return retrieveUserDtoFromDatabase(id).getEmail();
    }


    @Override
    public UserDto getUserById(Long id) {
        return retrieveUserDtoFromDatabase(id);
    }


    @Override
    public Boolean isValidUserById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public String getUserSurnameById(Long id) {
        return retrieveUserDtoFromDatabase(id).getSurname();
    }


    private String getUserEmailFromStorage() {
        return userStorage.getCurrentUserEmail();
    }

    private UserDto retrieveUserDtoFromDatabase(Object parameter) {
        UserDao userDao = findUser(parameter);
        if (userDao == null) {
            throw new IllegalStateException("User does not exist");
        }
        return userDao.toUserDto();
    }

    private UserDao findUser(Object parameter) {
        if (parameter instanceof Long) {
            return userRepository.findUserById((Long) parameter).orElse(null);
        } else if (parameter instanceof String) {
            return userRepository.findUserByEmail((String) parameter).orElse(null);
        }
        return null;
    }

    private Optional<UserDao> retrieveUserOptionalFromDatabase(String email) {
        return userRepository.findUserByEmail(email);
    }

    private void deleteUserFromDatabase(Long id) {
        userRepository.deleteById(id);
    }

    private Boolean doesUserExist(Long id) {
        return userRepository.existsById(id);
    }

    private Boolean doesUserExist(String email) {
        return userRepository.existsById(getUserId(email));
    }

    private UserRecord.CreateRequest createNewUserRequest(RegisterUser registerUser) {
        return new UserRecord.CreateRequest().setEmail(registerUser.getEmail()).setEmailVerified(false).setPassword(registerUser.getPassword()).setDisplayName(registerUser.getName() + " " + registerUser.getSurname());
    }

    private void createNewUserInAuthProviderService(RegisterUser registerUser, UserRecord.CreateRequest request) throws FirebaseAuthException {
        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        UserDao userDao = new UserDao(userRecord.getUid(), registerUser.getName(), registerUser.getSurname(), registerUser.getEmail());
        saveUserInDatabase(userDao);
    }

    private void saveUserInDatabase(UserDao userDao) {
        userRepository.save(userDao);
    }

    private Boolean userHasAdminPrivileges() {
        return userStorage.isAdministrator();
    }

    private List<UserDto> retrieveAllUsersFromDatabase() {
        return userRepository.findAll().stream().map(UserDao::toUserDto).toList();
    }
}
