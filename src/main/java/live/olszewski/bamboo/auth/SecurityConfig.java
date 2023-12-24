package live.olszewski.bamboo.auth;

import com.google.firebase.auth.FirebaseAuth;
import live.olszewski.bamboo.apiKeys.ApiKeysService;
import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * This class is responsible for the security configuration of the application.
 * It sets up the security filter chains for Firebase and API key authentication.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private FirebaseAuth firebaseAuth;

    @Autowired
    private UserStorage userStorage;
    @Autowired
    private UserService userService;
    @Autowired
    private ApiKeysService apiKeysService;
    @Autowired
    private SecurityPathsConfig securityPathsConfig;
    @Autowired
    private ApiResponseBuilder apiResponseBuilder;

    /**
     * This method creates a security filter chain for Firebase authentication.
     *
     * @param http The HttpSecurity object to configure.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain firebaseFilterChain(HttpSecurity http) throws Exception {
        return createFilterChain(http, securityPathsConfig.getJwtGet(), securityPathsConfig.getJwtPost(), securityPathsConfig.getJwtDelete(), firebaseFilter());
    }

    /**
     * This method creates a security filter chain for API key authentication.
     *
     * @param http The HttpSecurity object to configure.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain apiKeyFilterChain(HttpSecurity http) throws Exception {
        return createFilterChain(http, securityPathsConfig.getApiKeyGet(), securityPathsConfig.getApiKeyPost(), securityPathsConfig.getApiKeyDelete(), apiKeyFilter());
    }

    /**
     * This method creates a security filter chain with the specified paths and filter.
     *
     * @param http        The HttpSecurity object to configure.
     * @param getPaths    The paths for GET requests.
     * @param postPaths   The paths for POST requests.
     * @param deletePaths The paths for DELETE requests.
     * @param filter      The filter to add to the chain.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @SuppressWarnings("deprecation")
    private SecurityFilterChain createFilterChain(HttpSecurity http, String[] getPaths, String[] postPaths, String[] deletePaths, OncePerRequestFilter filter) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, getPaths).authenticated()
                        .requestMatchers(HttpMethod.POST, postPaths).authenticated()
                        .requestMatchers(HttpMethod.PUT, postPaths).authenticated()
                        .requestMatchers(HttpMethod.PATCH, postPaths).authenticated()
                        .requestMatchers(HttpMethod.DELETE, deletePaths).authenticated()

                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * This method creates a new instance of FirebaseFilter.
     *
     * @return The created FirebaseFilter.
     */
    @Bean
    public FirebaseFilter firebaseFilter() {
        return new FirebaseFilter(userStorage, firebaseAuth, userService, apiResponseBuilder);
    }

    /**
     * This method creates a new instance of ApiKeyFilter.
     *
     * @return The created ApiKeyFilter.
     */
    @Bean
    public ApiKeyFilter apiKeyFilter() {
        return new ApiKeyFilter(apiKeysService);
    }
}