package live.olszewski.bamboo.auth;

import com.google.firebase.auth.FirebaseAuth;
import live.olszewski.bamboo.apiKeys.ApiKeysService;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    ApiKeysService apiKeysService;

    @Value("${secured.paths.jwt.get}")
    private String[] jwtSecuredPathsGet;
    @Value("${secured.paths.jwt.post}")
    private String[] jwtSecuredPathsPost;
    @Value("${secured.paths.jwt.delete}")
    private String[] jwtSecuredPathsDelete;

    @Value("${secured.paths.api_key.get}")
    private String[] apiKeySecuredPathsGet;
    @Value("${secured.paths.api_key.post}")
    private String[] apiKeySecuredPathsPost;
    @Value("${secured.paths.api_key.delete}")
    private String[] apiKeySecuredPathsDelete;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, jwtSecuredPathsGet).authenticated()
                        .requestMatchers(HttpMethod.POST, jwtSecuredPathsPost).authenticated()
                        .requestMatchers(HttpMethod.DELETE, jwtSecuredPathsDelete).authenticated()
                        .requestMatchers(HttpMethod.PUT, jwtSecuredPathsPost).authenticated()
                        .requestMatchers(HttpMethod.PATCH, jwtSecuredPathsPost).authenticated())
                .addFilterBefore(firebaseFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, apiKeySecuredPathsGet).authenticated()
                        .requestMatchers(HttpMethod.POST, apiKeySecuredPathsPost).authenticated()
                        .requestMatchers(HttpMethod.DELETE, apiKeySecuredPathsDelete).authenticated()
                        .requestMatchers(HttpMethod.PUT, apiKeySecuredPathsPost).authenticated()
                        .requestMatchers(HttpMethod.PATCH, apiKeySecuredPathsPost).authenticated()
                )
                .addFilterBefore(apiKeyFilter(), UsernamePasswordAuthenticationFilter.class)

                .build();
    }

    @Bean
    public FirebaseFilter firebaseFilter() {
        return new FirebaseFilter(userStorage, firebaseAuth, userService);
    }

    @Bean
    public ApiKeyFilter apiKeyFilter() {
        return new ApiKeyFilter(apiKeysService);
    }
}

