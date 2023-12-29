package live.olszewski.bamboo.services.apiKey;

import org.springframework.stereotype.Service;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final int DEFAULT_API_KEY_LENGTH = 32;
    @Override
    public String generateApiKey(String seed) {
        java.util.Random random = new java.util.Random(seed.hashCode());
        return random.ints(DEFAULT_API_KEY_LENGTH, 0, ALLOWED_CHARACTERS.length())
                .mapToObj(ALLOWED_CHARACTERS::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
