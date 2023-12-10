package live.olszewski.bamboo.auth;

import com.google.firebase.auth.FirebaseAuth;
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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private FirebaseAuth firebaseAuth;

    @Autowired
    private UserStorage userStorage;

    @Autowired
    private UserService userService;

    @Value("${secured.paths.jwt.get}")
    private String[] jwtSecuredPathsGet;
    @Value("${secured.paths.jwt.post}")
    private String[] jwtSecuredPathsPost;
    @Value("${secured.paths.jwt.delete}")
    private String[] jwtSecuredPathsDelete;

    //TODO: session management is depracted and marked for removal in Spring Security 6.0. It needs a quick fix to stop future issues
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
                .build();
    }

    @Bean
    public FirebaseFilter firebaseFilter() {
        return new FirebaseFilter(userStorage, firebaseAuth, userService);
    }
}

