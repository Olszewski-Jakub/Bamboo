package live.olszewski.bamboo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    private static final String ROOT_PATH = "api/v1";

    @Bean
    public String userPath() {
        return ROOT_PATH + "/user";
    }

    @Bean
    public String registerPandaPath(){
        return ROOT_PATH + "/panda/register";
    }
}