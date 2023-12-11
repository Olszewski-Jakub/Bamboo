package live.olszewski.bamboo.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import live.olszewski.bamboo.apiKeys.ApiKeyDto;
import live.olszewski.bamboo.apiKeys.ApiKeysService;
import live.olszewski.bamboo.services.apiKey.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

public class ApiKeyFilter extends OncePerRequestFilter {

    private final ApiKeysService apiKeysService;

    public ApiKeyFilter(ApiKeysService apiKeysService) {
        this.apiKeysService = apiKeysService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getHeader("Authorization");
        if (apiKey != null && apiKey.startsWith("Bearer ")) {
            try {
                System.out.println("API-KEY");
                String apiKeyToken = apiKey.replace("Bearer ", "");
                ApiKeyDto apiKeyDto = apiKeysService.verifyApiKey(apiKeyToken);
                if (Objects.equals(apiKeyDto.getKey(), apiKeyToken)) {
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
