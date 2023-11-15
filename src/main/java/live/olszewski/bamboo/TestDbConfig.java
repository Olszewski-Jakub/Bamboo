package live.olszewski.bamboo;

import live.olszewski.bamboo.panda.PandaDao;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.user.UserDao;
import live.olszewski.bamboo.user.UserRepository;
import live.olszewski.bamboo.services.uuid.UUIDService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static java.lang.Long.parseLong;

@Configuration
public class TestDbConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, PandaRepository pandaRepository, UUIDService uuidService) {
        return args -> {
            UserDao admin = new UserDao("id-id-id-id", "Jakub", "Olszewski", "j.olszewski05@gmail.com", true);
            UserDao tester = new UserDao("test-test-test", "tester", "tester", "tester@tester.com");
            userRepository.saveAll(List.of(admin, tester));

            PandaDao panda1 = new PandaDao("Galway", "Dunlin Village", true, parseLong("1"));
            panda1.setUuid(uuidService.generateUUIDFromString(panda1.valuesForUuidGeneration()).toString());

            PandaDao panda2 = new PandaDao("Galway", "Goldcrest Village", true, parseLong("1"));
            panda2.setUuid(uuidService.generateUUIDFromString(panda2.valuesForUuidGeneration()).toString());

            PandaDao panda3 = new PandaDao("Galway", "Corrib Village", true, parseLong("2"));
            panda3.setUuid(uuidService.generateUUIDFromString(panda3.valuesForUuidGeneration()).toString());
            pandaRepository.saveAll(List.of(panda1, panda2, panda3));
        };
    }

}
