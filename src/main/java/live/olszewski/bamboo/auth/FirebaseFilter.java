package live.olszewski.bamboo.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.user.UserService;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * This class represents a filter for Firebase authentication.
 * It extends the OncePerRequestFilter class from the Spring Framework.
 */
public class FirebaseFilter extends OncePerRequestFilter {

    private final FirebaseAuth firebaseAuth;
    private final UserStorage userStorage;
    private final UserService userService;

    private final ApiResponseBuilder apiResponseBuilder;

    /**
     * Constructor for the FirebaseFilter class.
     *
     * @param userStorage  An instance of UserStorage to store user data.
     * @param firebaseAuth An instance of FirebaseAuth for Firebase authentication.
     * @param userService  An instance of UserService to handle user-related operations.
     */
    public FirebaseFilter(UserStorage userStorage, FirebaseAuth firebaseAuth, UserService userService, ApiResponseBuilder apiResponseBuilder) {
        this.userStorage = userStorage;
        this.firebaseAuth = firebaseAuth;
        this.userService = userService;
        this.apiResponseBuilder = apiResponseBuilder;
    }

    /**
     * This method is used to filter each request once.
     * It checks the Authorization header for a Bearer token and verifies it using Firebase.
     * If the token is valid, it sets the authentication in the SecurityContext and stores the user data.
     *
     * @param request     The HttpServletRequest object that contains the request the client made of the servlet.
     * @param response    The HttpServletResponse object that contains the response the servlet sends to the client.
     * @param filterChain The FilterChain object provided by the servlet container.
     * @throws ServletException If the request for the GET could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request.
     */
    @Override
    public void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String idToken = extractToken(request.getHeader("Authorization"));
        if (idToken != null) {
            try {
                FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
                String uid = decodedToken.getUid();
                if (uid != null) {
                    setAuthenticationAndStoreUser(decodedToken, uid);
                }
            } catch (FirebaseAuthException e) {
                SecurityContextHolder.clearContext();

            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * This method is used to extract the token from the Authorization header.
     *
     * @param authorizationHeader The Authorization header from the request.
     * @return The token if it exists, null otherwise.
     */
    private String extractToken(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.startsWith("Bearer ") ? authorizationHeader.replace("Bearer ", "") : null;
    }

    /**
     * This method is used to set the authentication in the SecurityContext and store the user data.
     *
     * @param decodedToken The decoded Firebase token.
     * @param uid          The user ID from the decoded token.
     */
    private void setAuthenticationAndStoreUser(FirebaseToken decodedToken, String uid) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(uid, null, null));
        userStorage.setCurrentUser(decodedToken.getName(), userService.getUserSurnameById(userService.getUserId(decodedToken.getEmail())), decodedToken.getEmail(), uid, userService.isAdministrator(decodedToken.getEmail()), userService.getUserId(decodedToken.getEmail()));
    }
}

