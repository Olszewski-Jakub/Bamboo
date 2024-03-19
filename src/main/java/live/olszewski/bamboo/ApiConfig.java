package live.olszewski.bamboo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is a configuration class that provides the API paths for different endpoints.
 * It uses the @Configuration annotation to indicate that it is a configuration class.
 * It uses the @Bean annotation to define beans that will be managed by the Spring container.
 */
@Configuration
public class ApiConfig {

    // The root path for all API endpoints
    private static final String ROOT_PATH = "api/v1";

    /**
     * This method returns the API path for the user endpoint.
     *
     * @return the API path for the user endpoint
     */
    @Bean
    public String userPath() {
        return ROOT_PATH + "/user";
    }

    /**
     * This method returns the API path for the panda device endpoint.
     *
     * @return the API path for the panda device endpoint
     */
    @Bean
    public String pandaDevicePath() {
        return ROOT_PATH + "/panda/device";
    }

    /**
     * This method returns the API path for the register panda endpoint.
     *
     * @return the API path for the register panda endpoint
     */
    @Bean
    public String registerPandaPath() {
        return ROOT_PATH + "/panda/register";
    }

    /**
     * This method returns the API path for the API key endpoint.
     *
     * @return the API path for the API key endpoint
     */
    @Bean
    public String apiKeyPath() {
        return ROOT_PATH + "/apiKey";
    }

    /**
     * This method returns the API path for the panda config endpoint.
     *
     * @return the API path for the panda config endpoint
     */
    @Bean
    public String pandaConfigPath() {
        return ROOT_PATH + "/panda/config";
    }

    /**
     * This method returns the API path for the panda connection status endpoint.
     *
     * @return the API path for the panda connection status endpoint
     */
    @Bean
    public String pandaConnectionStatusPath() {
        return ROOT_PATH + "/panda/connection/status";
    }

    /**
     * This method returns the API path for the panda data collection endpoint.
     *
     * @return the API path for the panda data collection endpoint
     */
    @Bean
    public String pandaDataCollectionPath() {
        return ROOT_PATH + "/panda/data/send";
    }

    @Bean
    public String spacePath() {
        return ROOT_PATH + "/space";
    }
}