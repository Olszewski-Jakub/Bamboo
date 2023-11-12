package live.olszewski.bamboo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            UserDao admin = new UserDao("id-id-id-id", "Jakub", "Olszewski", "j.olszewski05@gmail.com");
            UserDao tester = new UserDao("test-test-test", "tester", "tester", "tester@tester.com");
            userRepository.saveAll(List.of(admin, tester));
        };
    }

}
