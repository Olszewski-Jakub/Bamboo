package live.olszewski.bamboo.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for holding the configuration properties related to secured paths.
 * It is annotated with @Component to indicate that it is a Spring component.
 * The @ConfigurationProperties annotation is used to specify the prefix of the properties that will be mapped to this class.
 */
@Component
@ConfigurationProperties(prefix = "secured.paths")
public class SecurityPathsConfig {

    private String[] jwtGet;
    private String[] jwtPost;
    private String[] jwtDelete;
    private String[] apiKeyGet;
    private String[] apiKeyPost;
    private String[] apiKeyDelete;

    // getters and setters

    /**
     * Returns the paths for JWT GET requests.
     *
     * @return An array of paths for JWT GET requests.
     */
    public String[] getJwtGet() {
        return jwtGet;
    }

    /**
     * Sets the paths for JWT GET requests.
     *
     * @param jwtGet An array of paths for JWT GET requests.
     */
    public void setJwtGet(String[] jwtGet) {
        this.jwtGet = jwtGet;
    }

    /**
     * Returns the paths for JWT POST requests.
     *
     * @return An array of paths for JWT POST requests.
     */
    public String[] getJwtPost() {
        return jwtPost;
    }

    /**
     * Sets the paths for JWT POST requests.
     *
     * @param jwtPost An array of paths for JWT POST requests.
     */
    public void setJwtPost(String[] jwtPost) {
        this.jwtPost = jwtPost;
    }

    /**
     * Returns the paths for JWT DELETE requests.
     *
     * @return An array of paths for JWT DELETE requests.
     */
    public String[] getJwtDelete() {
        return jwtDelete;
    }

    /**
     * Sets the paths for JWT DELETE requests.
     *
     * @param jwtDelete An array of paths for JWT DELETE requests.
     */
    public void setJwtDelete(String[] jwtDelete) {
        this.jwtDelete = jwtDelete;
    }

    /**
     * Returns the paths for API key GET requests.
     *
     * @return An array of paths for API key GET requests.
     */
    public String[] getApiKeyGet() {
        return apiKeyGet;
    }

    /**
     * Sets the paths for API key GET requests.
     *
     * @param apiKeyGet An array of paths for API key GET requests.
     */
    public void setApiKeyGet(String[] apiKeyGet) {
        this.apiKeyGet = apiKeyGet;
    }

    /**
     * Returns the paths for API key POST requests.
     *
     * @return An array of paths for API key POST requests.
     */
    public String[] getApiKeyPost() {
        return apiKeyPost;
    }

    /**
     * Sets the paths for API key POST requests.
     *
     * @param apiKeyPost An array of paths for API key POST requests.
     */
    public void setApiKeyPost(String[] apiKeyPost) {
        this.apiKeyPost = apiKeyPost;
    }

    /**
     * Returns the paths for API key DELETE requests.
     *
     * @return An array of paths for API key DELETE requests.
     */
    public String[] getApiKeyDelete() {
        return apiKeyDelete;
    }

    /**
     * Sets the paths for API key DELETE requests.
     *
     * @param apiKeyDelete An array of paths for API key DELETE requests.
     */
    public void setApiKeyDelete(String[] apiKeyDelete) {
        this.apiKeyDelete = apiKeyDelete;
    }
}