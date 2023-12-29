package live.olszewski.bamboo.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import live.olszewski.bamboo.apiKeys.ApiKeyDto;
import live.olszewski.bamboo.apiKeys.ApiKeysService;
import live.olszewski.bamboo.auth.pandaStorage.PandaStorage;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * This class represents the API Key Filter.
 * It extends OncePerRequestFilter to provide a method to filter API keys.
 */

public class ApiKeyFilter extends OncePerRequestFilter {

    private final ApiKeysService apiKeysService;
    private final PandaStorage pandaStorage;

    /**
     * Constructor for the ApiKeyFilter class.
     *
     * @param apiKeysService The service to handle API keys.
     */
    public ApiKeyFilter(ApiKeysService apiKeysService, PandaStorage pandaStorage) {
        this.apiKeysService = apiKeysService;
        this.pandaStorage = pandaStorage;
    }

    /**
     * This method is used to filter API keys.
     * It checks if the API key in the request header is valid.
     * If the API key is valid, it sets the authentication in the security context.
     * If the API key is not valid, it clears the security context.
     *
     * @param request     The HTTP request.
     * @param response    The HTTP response.
     * @param filterChain The filter chain.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getHeader("Authorization");
        if (apiKey != null && apiKey.startsWith("Bearer ")) {
            try {
                String apiKeyToken = apiKey.replace("Bearer ", "");
                ApiKeyDto apiKeyDto = apiKeysService.verifyApiKey(apiKeyToken);
                if (Objects.equals(apiKeyDto.getKey(), apiKeyToken)) {
                    // If the API key is valid, set the authentication in the security context
                    pandaStorage.setCurrentPanda(apiKeyToken);
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(apiKeyDto.getPanda(), null, null);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }

        }
        filterChain.doFilter(request, response);

    }
}
