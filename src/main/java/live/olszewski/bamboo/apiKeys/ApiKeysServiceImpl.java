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

    @Autowired
    private PandaOwnershipCkeck pandaOwnershipCkeck;

    @Autowired
    private PandaService pandaService;

    @Autowired
    private ApiKeyService apiKeyService;

    @Autowired
    private ApiKeysRepository apiKeysRepository;

    @Autowired
    private UserStorage userStorage;

    /**
     * This method checks if all values in a list of Booleans are false.
     *
     * @param list The list of Booleans to check.
     * @return true if all values are false, otherwise false.
     */
    private static boolean areAllFalse(List<Boolean> list) {
        for (boolean value : list) {
            if (value) {
                return false; // If any value is true, return false
            }
        }
        return true; // All values are false
    }

    /**
     * This method is used to add a new API key.
     *
     * @param pandaId The ID of the panda to add the API key.
     * @return The new API key.
     */
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

    /**
     * This method checks if an API key with a given ID exists.
     *
     * @param id The ID of the API key to check.
     */
    private void doesApiKeyWithIdExists(Long id) {
        boolean exists = apiKeysRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Api key with id " + id + " deos not exists");
        }
    }

    /**
     * This method retrieves an API key DAO by its ID.
     *
     * @param id The ID of the API key to retrieve.
     * @return The API key DAO.
     */
    private ApiKeyDao getApiKeyDaoById(Long id) {
        doesApiKeyWithIdExists(id);
        Optional<ApiKeyDao> apiKeysDaoOptional = apiKeysRepository.findById(id);
        if (apiKeysDaoOptional.isEmpty())
            throw new IllegalStateException("Api key with id " + id + " deos not exists");
        return apiKeysDaoOptional.get();
    }

    /**
     * This method is used to deactivate an API key.
     *
     * @param id The ID of the API key to deactivate.
     */
    @Override
    public void deactivateApiKey(Long id) {
        ApiKeyDao apiKeyDao = getApiKeyDaoById(id);
        apiKeyDao.setActive(false);
        apiKeysRepository.save(apiKeyDao);
    }

    /**
     * This method is used to activate an API key.
     *
     * @param id The ID of the API key to activate.
     */
    @Override
    public void activateApiKey(Long id) {
        ApiKeyDao apiKeyDao = getApiKeyDaoById(id);
        apiKeyDao.setActive(true);
        apiKeysRepository.save(apiKeyDao);
    }

    /**
     * This method retrieves API keys by their owner.
     * @return A list of API key DTOs.
     */
    @Override
    public List<ApiKeyDto> getApiKeyByOwner() {
        Long ownerId = userStorage.getCurrentUserId();
        List<ApiKeyDao> apiKeyDaos = apiKeysRepository.findByOwner(ownerId);
        if (apiKeyDaos.isEmpty()) {
            throw new IllegalStateException("User with id " + ownerId + " has no api keys");
        }
        return apiKeyDaos.stream().map(ApiKeyDao::toDto).toList();
    }

    /**
     * This method retrieves an active API key by the panda it is associated with.
     * @param pandaId The ID of the panda to get the API key.
     * @return The API key DTO.
     */
    @Override
    public ApiKeyDto getApiKeyByPanda(String pandaId) {
        Long pandaIdLong = parseLong(pandaId);
        Optional<ApiKeyDao> apiKeyDaoOptional = apiKeysRepository.findByPandaAndActive(pandaIdLong);
        if (apiKeyDaoOptional.isEmpty()) {
            throw new IllegalStateException("Panda with id " + pandaId + " has no active api key");
        }
        return apiKeyDaoOptional.get().toDto();
    }

    /**
     * This method verifies an API key.
     * @param apiKey The API key to verify.
     * @return The API key DTO.
     */
    @Override
    public ApiKeyDto verifyApiKey(String apiKey) {

        Optional<ApiKeyDao> apiKeyDao = apiKeysRepository.findByKey(apiKey);
        if (apiKeyDao.isEmpty()) {
            throw new IllegalStateException("Api key " + apiKey + " does not exists");
        }
        ApiKeyDto apiKeyDto = apiKeyDao.get().toDto();


        return apiKeyDto;
    }

}
