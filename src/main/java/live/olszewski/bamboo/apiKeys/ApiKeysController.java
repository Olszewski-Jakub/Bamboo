package live.olszewski.bamboo.apiKeys;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class represents the API Keys Controller.
 * It handles HTTP requests related to API keys.
 */
@RestController
@RequestMapping(path = "#{apiConfig.apiKeyPath}")
@Tag(name = "Api key", description = "Api key related endpoints")
public class ApiKeysController {

    @Autowired
    private ApiKeysService apiKeysService;

    /**
     * This method handles the HTTP POST request to add a new API key.
     *
     * @param pandaId The ID of the panda to add the API key.
     * @return A string representing the status of the operation.
     */
    @PostMapping(path = "/add")
    @Operation(
            description = "Add new api key",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    @Parameter(
            name = "pandaId",
            description = "Id of panda to add api key",
            required = true,
            example = "1"
    )
    public String addNewApiKey(String pandaId) {
        return apiKeysService.addNewApiKey(pandaId);
    }

    /**
     * This method handles the HTTP PATCH request to deactivate an API key.
     *
     * @param id The ID of the API key to deactivate.
     */
    @PatchMapping(path = "/deactivate")
    @Operation(
            description = "Deactivate api key",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }

    )
    @Parameter(
            name = "id",
            description = "Id of api key to deactivate",
            required = true,
            example = "1"
    )
    public void deactivateApiKey(Long id) {
        apiKeysService.deactivateApiKey(id);
    }

    /**
     * This method handles the HTTP PATCH request to activate an API key.
     *
     * @param id The ID of the API key to activate.
     */
    @PatchMapping(path = "/activate")
    @Operation(
            description = "Activate api key",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }

    )
    @Parameter(
            name = "id",
            description = "Id of api key to activate",
            required = true,
            example = "1"
    )
    public void activateApiKey(Long id) {
        apiKeysService.activateApiKey(id);
    }

    /**
     * This method handles the HTTP GET request to retrieve API keys by owner.
     *
     * @return A list of API key DTOs.
     */
    @GetMapping(path = "/get/byOwner")
    @Operation(
            description = "Get api keys by owner",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Api key",
                                                    value = "{\n" +
                                                            "  \"id\": 1,\n" +
                                                            "  \"key\": \"string\",\n" +
                                                            "  \"owner\": 1,\n" +
                                                            "  \"panda\": 1,\n" +
                                                            "  \"created\": \"2021-05-30T12:00:00\",\n" +
                                                            "  \"active\": true\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    public List<ApiKeyDto> getApiKeyByOwner() {
        return apiKeysService.getApiKeyByOwner();
    }

    /**
     * This method handles the HTTP GET request to retrieve an active API key by panda.
     *
     * @param pandaId The ID of the panda to get the API key.
     * @return An API key DTO.
     */
    @GetMapping(path = "/get/byPanda")
    @Operation(
            description = "Get active api key by panda",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Api key",
                                                    value = "{\n" +
                                                            "  \"id\": 1,\n" +
                                                            "  \"key\": \"string\",\n" +
                                                            "  \"owner\": 1,\n" +
                                                            "  \"panda\": 1,\n" +
                                                            "  \"created\": \"2021-05-30T12:00:00\",\n" +
                                                            "  \"active\": true\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "There is active panda api key"
                    )
            }
    )
    @Parameter(
            name = "pandaId",
            description = "Id of panda to get api key",
            required = true,
            example = "1"
    )
    public ApiKeyDto getApiKeyByPanda(String pandaId) {
        return apiKeysService.getApiKeyByPanda(pandaId);
    }
}