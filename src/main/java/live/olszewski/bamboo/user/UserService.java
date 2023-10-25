package live.olszewski.bamboo.user;

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

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByUID(user.getUID());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("User already exists");
        }
        userRepository.save(user);
        System.out.println(user);
    }

    public void deleteUser(Long id){
        boolean exists = userRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("User with id " + id + " deos not exists");
        }
        userRepository.deleteById(id);
    }
}
