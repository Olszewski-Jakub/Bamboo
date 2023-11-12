package live.olszewski.bamboo.auth;

import com.google.firebase.auth.FirebaseAuth;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
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
    @Value("${secured.paths.get}")
    private String[] securedPathsGet;

    @Value("${secured.paths.post}")
    private String[] securedPathsPost;
    @Value("${secured.paths.delete}")
    private String[] securedPathsDelete;

    //TODO: session management is depracted and marked for removal in Spring Security 6.0. It needs a quick fix to stop future issues
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(AbstractHttpConfigurer::disable)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                authorizeRequests().requestMatchers(HttpMethod.GET, securedPathsGet).authenticated()
                .requestMatchers(HttpMethod.POST, securedPathsPost).authenticated()
                .requestMatchers(HttpMethod.DELETE, securedPathsDelete).authenticated().and()
                .addFilterBefore(new FirebaseFilter(userStorage,firebaseAuth), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

