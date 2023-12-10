package live.olszewski.bamboo.apiKeys;

import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.panda.device.PandaService;
import live.olszewski.bamboo.panda.ownershipCheck.PandaOwnershipCkeck;
import live.olszewski.bamboo.services.apiKey.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Long.parseLong;

@Service
public class ApiKeysServiceImpl implements ApiKeysService {
    private final ApiKeyService apiKeyService;
    private final ApiKeysRepository apiKeysRepository;
    private final PandaService pandaService;
    private final UserStorage userStorage;

    @Autowired
    public ApiKeysServiceImpl(ApiKeyService apiKeyService, ApiKeysRepository apiKeysRepository, UserStorage userStorage, PandaService pandaService) {
        this.apiKeyService = apiKeyService;
        this.apiKeysRepository = apiKeysRepository;
        this.userStorage = userStorage;
        this.pandaService = pandaService;
    }

    @Autowired
    private PandaOwnershipCkeck pandaOwnershipCkeck;
    private static boolean areAllFalse(List<Boolean> list) {
        for (boolean value : list) {
            if (value) {
                return false; // If any value is true, return false
            }
        }
        return true; // All values are false
    }

    @Override
    public String addNewApiKey(String pandaId) {
        Long pandaIdLong = parseLong(pandaId);
        boolean isPandaOwner = pandaOwnershipCkeck.isPandaOwner(pandaIdLong);
        if (!isPandaOwner) {
            throw new IllegalStateException("User is not owner of device");
        }
        List<ApiKeyDao> apiKeyDaos = apiKeysRepository.findByPanda(pandaIdLong);
        List<Boolean> activeApiKeys = apiKeyDaos.stream().map(ApiKeyDao::getActive).toList();
        if (!areAllFalse(activeApiKeys)) {
            throw new IllegalStateException("Panda with id " + pandaId + " has active api key. To create new one ensure old api keys are deactivated");
        }
        ApiKeyDao apiKeyDao = new ApiKeyDao();
        apiKeyDao.setPanda(pandaIdLong);
        apiKeyDao.setOwner(userStorage.getCurrentUserId());
        String apiKey = apiKeyService.generateApiKey(apiKeyDao.getSeed());
        apiKeyDao.setKey(apiKey);
        apiKeysRepository.save(apiKeyDao);
        Boolean updated = pandaService.updatePandaApiKey(pandaIdLong, apiKey);
        if (!updated)
            throw new IllegalStateException("Panda with id " + pandaId + " has no api key");
        return apiKey;
    }

    private void doesApiKeyWithIdExists(Long id) {
        boolean exists = apiKeysRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Api key with id " + id + " deos not exists");
        }
    }

    private ApiKeyDao getApiKeyDaoById(Long id) {
        doesApiKeyWithIdExists(id);
        Optional<ApiKeyDao> apiKeysDaoOptional = apiKeysRepository.findById(id);
        if (apiKeysDaoOptional.isEmpty())
            throw new IllegalStateException("Api key with id " + id + " deos not exists");
        return apiKeysDaoOptional.get();
    }

    @Override
    public void deactivateApiKey(Long id) {
        ApiKeyDao apiKeyDao = getApiKeyDaoById(id);
        apiKeyDao.setActive(false);
        apiKeysRepository.save(apiKeyDao);
    }

    @Override
    public void activateApiKey(Long id) {
        ApiKeyDao apiKeyDao = getApiKeyDaoById(id);
        apiKeyDao.setActive(true);
        apiKeysRepository.save(apiKeyDao);
    }

    @Override
    public List<ApiKeyDto> getApiKeyByOwner() {
        Long ownerId = userStorage.getCurrentUserId();
        List<ApiKeyDao> apiKeyDaos = apiKeysRepository.findByOwner(ownerId);
        if (apiKeyDaos.isEmpty()) {
            throw new IllegalStateException("User with id " + ownerId + " has no api keys");
        }
        return apiKeyDaos.stream().map(ApiKeyDao::toDto).toList();
    }

    @Override
    public ApiKeyDto getApiKeyByPanda(String pandaId) {
        Long pandaIdLong = parseLong(pandaId);
        Optional<ApiKeyDao> apiKeyDaoOptional = apiKeysRepository.findByPandaAndActive(pandaIdLong);
        if (apiKeyDaoOptional.isEmpty()) {
            throw new IllegalStateException("Panda with id " + pandaId + " has no active api key");
        }
        return apiKeyDaoOptional.get().toDto();
    }

}
