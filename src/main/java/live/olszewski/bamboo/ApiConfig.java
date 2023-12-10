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
    public String pandaDevicePath(){
        return ROOT_PATH + "/panda/device";
    }

    @Bean
    public String registerPandaPath(){
        return ROOT_PATH + "/panda/register";
    }

    @Bean
    public String apiKeyPath(){
        return ROOT_PATH + "/apiKey";
    }

    @Bean
    public String pandaConfigPath(){
        return ROOT_PATH + "/panda/config";
    }
}