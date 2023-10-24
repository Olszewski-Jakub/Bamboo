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
            User admin = new User("id-id-id-id", "Jakub", "Olszewski", "j.olszewski@gmail.com");
            User tester = new User("test-test-test", "tester", "tester", "tester@tester.com");
            userRepository.saveAll(List.of(admin, tester));
        };
    }

}
