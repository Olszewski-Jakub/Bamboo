package live.olszewski.bamboo.apiKeys;

import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.panda.device.PandaService;
import live.olszewski.bamboo.services.apiKey.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Long.parseLong;


public interface ApiKeysService {

    String addNewApiKey(String pandaId);

    void deactivateApiKey(Long id);

    void activateApiKey(Long id);

    ApiKeyDto getApiKeyByPanda(String pandaId);

    List<ApiKeyDto> getApiKeyByOwner();

    ApiKeyDto verifyApiKey(String apiKey);

}
