package live.olszewski.bamboo;

import live.olszewski.bamboo.services.apiKey.ApiKeyService;
import live.olszewski.bamboo.services.apiKey.ApiKeyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApiKeyServiceTest {

    @Autowired
    ApiKeyService apiKeyService;

    @Test
    public void generatesApiKeyOfCorrectLength() {
        String seed = "testSeed";
        String apiKey = apiKeyService.generateApiKey(seed);
        assertEquals(ApiKeyServiceImpl.DEFAULT_API_KEY_LENGTH, apiKey.length());
    }

    @Test
    public void generatesDifferentApiKeysForDifferentSeeds() {
        String seed1 = "testSeed1";
        String seed2 = "testSeed2";
        String apiKey1 = apiKeyService.generateApiKey(seed1);
        String apiKey2 = apiKeyService.generateApiKey(seed2);
        assertNotEquals(apiKey1, apiKey2);
    }

    @Test
    public void generatesSameApiKeyForSameSeed() {
        String seed = "testSeed";
        String apiKey1 = apiKeyService.generateApiKey(seed);
        String apiKey2 = apiKeyService.generateApiKey(seed);
        assertEquals(apiKey1, apiKey2);
    }

}
