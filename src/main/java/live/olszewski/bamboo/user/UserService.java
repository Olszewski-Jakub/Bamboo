package live.olszewski.bamboo.user;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User registerUser) {
        Optional<User> userOptional = userRepository.findUserByEmail(registerUser.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("User already exists");
        }
//        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
//                .setEmail(registerUser.getEmail())
//                .setEmailVerified(false)
//                .setPassword(registerUser.getPassword())
//                .setDisplayName(registerUser.getName() + " " + registerUser.getSurname());

//        try {
//            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
//            User user = new User(userRecord.getUid(), registerUser.getName(), registerUser.getSurname(), registerUser.getEmail());
            userRepository.save(registerUser);
            System.out.println(registerUser);
//        } catch (FirebaseAuthException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("User with id " + id + " deos not exists");
        }
        userRepository.deleteById(id);
    }
}
