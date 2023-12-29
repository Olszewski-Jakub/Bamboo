package live.olszewski.bamboo.services.apiKey;

/**
 * This interface defines a method for generating an API key.
 */
public interface ApiKeyService {

    /**
     * Generates an API key using the provided seed.
     *
     * @param seed The seed used to generate the API key.
     * @return The generated API key.
     */
    String generateApiKey(String seed);

}