package live.olszewski.bamboo.panda.config;

import com.google.gson.Gson;
import live.olszewski.bamboo.apiKeys.ApiKeysService;
import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.panda.PandaDto;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.services.jsonExporter.JsonExporterService;
import live.olszewski.bamboo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * Service class for handling Panda configuration.
 */

public class PandaConfigServiceImpl implements PandaConfigService {

    @Autowired
    private PandaRepository pandaRepository;

    @Autowired
    private JsonExporterService jsonExporterService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApiResponseBuilder apiResponseBuilder;

    @Autowired
    private ApiKeysService apiKeysService;

    /**
     * Method to get the owner of a Panda device by ID.
     *
     * @param id the ID of the Panda device
     * @return the email of the owner
     */
    private String getOwner(Long id) {
        return userService.getUserEmailById(id);
    }

    /**
     * Method to get the Panda configuration DTO by Panda ID.
     *
     * @param pandaId the ID of the Panda device
     * @return the Panda configuration DTO
     */
    private PandaConfigDto getPandaConfigDto(Long pandaId) {
        PandaDto pandaDto = pandaRepository.findDeviceById(pandaId)
                .orElseThrow(() -> new IllegalStateException("Device with id " + pandaId + " does not exists"))
                .toDto();
        PandaConfigDto pandaConfigDto = pandaDto.toConfigDto();
        pandaConfigDto.setOwner(getOwner(pandaDto.getOwner()));
        return pandaConfigDto;
    }

    /**
     * Method to download the Panda configuration.
     *
     * @param pandaId the ID of the Panda device
     * @return the Panda configuration as a byte array in a ResponseEntity
     */
    @Override
    public ResponseEntity<byte[]> downloadPandaConfig(Long pandaId) {
        PandaConfigDto pandaConfigDto = getPandaConfigDto(pandaId);
        byte[] pandaConfigJsonBytes = jsonExporterService.export(pandaConfigDto).getBytes();
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=panda-" + pandaConfigDto.getName() + "-config.json")
                .body(pandaConfigJsonBytes);
    }

    /**
     * Method to verify the Panda configuration.
     *
     * @param pandaConfigJson the JSON string of the Panda configuration
     * @return a ResponseEntity containing the ApiResponseDto
     */
    @Override
    public ResponseEntity<ApiResponseDto<?>> verifyPandaConfig(String pandaConfigJson) {
        Gson gson = new Gson();
        PandaConfigDto pandaConfigDto;

        try {
            pandaConfigDto = gson.fromJson(pandaConfigJson, PandaConfigDto.class);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(apiResponseBuilder.buildErrorResponse(400, "Invalid json"));
        }

        try {
            apiKeysService.verifyApiKey(pandaConfigDto.getApi_key());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(apiResponseBuilder.buildErrorResponse(400, "Invalid api key"));
        }

        if (userService.isValidUserById(Long.parseLong(pandaConfigDto.getOwner()))) {
            return ResponseEntity.status(400).body(apiResponseBuilder.buildErrorResponse(400, "Invalid owner"));
        }

        try {
            isCorrectPandaOwner(pandaConfigDto.getUuid(), pandaConfigDto.getOwner());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(apiResponseBuilder.buildErrorResponse(400, e.getMessage()));
        }
        return ResponseEntity.ok(apiResponseBuilder.buildSuccessResponse(200, "Successfully verified panda config", pandaConfigDto));
    }

    /**
     * Method to check if the owner of a Panda device is correct.
     *
     * @param pandaUUID the UUID of the Panda device
     * @param ownerId   the ID of the owner
     */
    private void isCorrectPandaOwner(String pandaUUID, String ownerId) {
        String owner = getOwner(pandaRepository.findDeviceByUUID(pandaUUID)
                .orElseThrow(() -> new IllegalStateException("Device with UUID " + pandaUUID + " does not exist"))
                .getOwner());
        if (!owner.equals(ownerId)) {
            throw new IllegalStateException("Owner is incorrect for panda UUID");
        }
    }
}
