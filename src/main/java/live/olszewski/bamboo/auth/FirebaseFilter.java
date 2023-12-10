package live.olszewski.bamboo.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.user.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class FirebaseFilter extends OncePerRequestFilter {

    private final FirebaseAuth firebaseAuth;
    private final UserStorage userStorage;

    private final UserService userService;

    public FirebaseFilter(UserStorage userStorage, FirebaseAuth firebaseAuth, UserService userService) {
        this.userStorage = userStorage;
        this.firebaseAuth = firebaseAuth;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String idToken = authorizationHeader.replace("Bearer ", "");
            try {
                FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
                String uid = decodedToken.getUid();
                if (uid != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(uid, null, null);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    String email = decodedToken.getEmail();
                    userStorage.setCurrentUser(decodedToken.getName(),email, uid,userService.isAdministrator(email), userService.getUserId(email));
                }
            } catch (FirebaseAuthException e) {
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }
}


