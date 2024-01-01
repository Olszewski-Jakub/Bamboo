package live.olszewski.bamboo.apiKeys;

import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.apiResponse.MessageService;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.panda.device.PandaService;
import live.olszewski.bamboo.panda.objects.PandaDao;
import live.olszewski.bamboo.panda.ownershipCheck.PandaOwnershipCkeck;
import live.olszewski.bamboo.services.apiKey.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private ApiResponseBuilder builder;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PandaRepository pandaRepository;

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
    public ResponseEntity<ApiResponseDto<?>> addNewApiKey(String pandaId) {
        Long pandaIdLong = parseLong(pandaId);
        boolean isPandaOwner = pandaOwnershipCkeck.isPandaOwner(pandaIdLong);
        if (!isPandaOwner) {
            return builder.error().code403(messageService.getMessage("user.not.owner"));
        }
        List<ApiKeyDao> apiKeyDaos = apiKeysRepository.findByPanda(pandaIdLong);
        List<Boolean> activeApiKeys = apiKeyDaos.stream().map(ApiKeyDao::getActive).toList();
        if (!areAllFalse(activeApiKeys)) {
            return builder.error().code403(messageService.getMessage("api.key.has.active.api.key", pandaId));
        }
        ApiKeyDao apiKeyDao = new ApiKeyDao();
        apiKeyDao.setPanda(pandaIdLong);
        apiKeyDao.setOwner(userStorage.getCurrentUserId());
        String apiKey = apiKeyService.generateApiKey(apiKeyDao.getSeed());
        apiKeyDao.setKey(apiKey);
        apiKeysRepository.save(apiKeyDao);
        Boolean updated = pandaService.updatePandaApiKey(pandaIdLong, apiKey);
        if (!updated)
            return builder.error().code500(messageService.getMessage("api.key.does.not.exist"));
        return builder.success().code200(messageService.getMessage("api.key.created", pandaId), apiKey);
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
    public ResponseEntity<ApiResponseDto<?>> deactivateApiKey(Long id) {
        ApiKeyDao apiKeyDao = getApiKeyDaoById(id);
        apiKeyDao.setActive(false);
        apiKeysRepository.save(apiKeyDao);
        return builder.success().code200(messageService.getMessage("api.key.deactivated", id), null);
    }

    /**
     * This method is used to activate an API key.
     *
     * @param id The ID of the API key to activate.
     */
    @Override
    public ResponseEntity<ApiResponseDto<?>> activateApiKey(Long id) {
        ApiKeyDao apiKeyDao = getApiKeyDaoById(id);
        apiKeyDao.setActive(true);
        apiKeysRepository.save(apiKeyDao);
        return builder.success().code200(messageService.getMessage("api.key.activated", id), null);
    }

    /**
     * This method retrieves API keys by their owner.
     *
     * @return A list of API key DTOs.
     */
    @Override
    public ResponseEntity<ApiResponseDto<?>> getApiKeyByOwner() {
        Long ownerId = userStorage.getCurrentUserId();
        List<ApiKeyDao> apiKeyDaos = apiKeysRepository.findByOwner(ownerId);
        if (apiKeyDaos.isEmpty()) {
            return builder.error().code403(messageService.getMessage("user.has.no.api.keys", ownerId));
        }
        return builder.success().code200(messageService.getMessage("api.key.retrieve.by.owner", ownerId), apiKeyDaos.stream().map(ApiKeyDao::toDto).toList());
    }

    /**
     * This method retrieves an active API key by the panda it is associated with.
     *
     * @param pandaId The ID of the panda to get the API key.
     * @return The API key DTO.
     */
    @Override
    public ResponseEntity<ApiResponseDto<?>> getApiKeyByPanda(String pandaId) {
        Long pandaIdLong = parseLong(pandaId);
        Optional<ApiKeyDao> apiKeyDaoOptional = apiKeysRepository.findByPandaAndActive(pandaIdLong);
        if (apiKeyDaoOptional.isEmpty()) {
            return builder.error().code403(messageService.getMessage("panda.has.no.active.api.key", pandaId));
        }
        return builder.success().code200(messageService.getMessage("api.key.retrieve.by.panda", pandaId), apiKeyDaoOptional.get().toDto());
    }

    /**
     * This method verifies an API key.
     *
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

    /**
     * This method retrieves a PandaDao object by its associated API key.
     *
     * @param apiKey The API key associated with the PandaDao object.
     * @return The PandaDao object if it exists, null otherwise.
     */
    @Override
    public PandaDao getPandaByApiKey(String apiKey) {
        ApiKeyDao apiKeyDao = apiKeysRepository.findByKey(apiKey).orElse(null);
        if (apiKeyDao == null)
            return null;
        return pandaRepository.findById(apiKeyDao.getPanda()).orElse(null);
    }
}
