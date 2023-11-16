package live.olszewski.bamboo.services.apiKey;

import org.springframework.stereotype.Service;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final int DEFAULT_API_KEY_LENGTH = 32;
    @Override
    public String generateApiKey(String seed) {
        StringBuilder apiKeyBuilder = new StringBuilder();

        // Use the hash code of the string as a seed
        long hashedSeed = seed.hashCode();
        java.util.Random random = new java.util.Random(hashedSeed);

        for (int i = 0; i < DEFAULT_API_KEY_LENGTH; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            apiKeyBuilder.append(ALLOWED_CHARACTERS.charAt(randomIndex));
        }

        return apiKeyBuilder.toString();
    }
}
