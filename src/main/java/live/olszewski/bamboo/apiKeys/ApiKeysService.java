package live.olszewski.bamboo.apiKeys;

import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.panda.device.PandaService;
import live.olszewski.bamboo.services.apiKey.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Long.parseLong;


interface ApiKeysService {

    public String addNewApiKey(String pandaId);

    public void deactivateApiKey(Long id);

    public void activateApiKey(Long id);

    public ApiKeyDto getApiKeyByPanda(String pandaId);

    public List<ApiKeyDto> getApiKeyByOwner();
}
