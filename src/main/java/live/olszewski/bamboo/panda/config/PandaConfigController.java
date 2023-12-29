package live.olszewski.bamboo.panda.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller handles requests related to Panda configuration.
 */
@RestController
@RequestMapping(path = "#{apiConfig.pandaConfigPath}")
@Tag(name = "Panda Configuration", description = "Endpoint to acquire panda configuration")
public class PandaConfigController {

    @Autowired
    private PandaConfigService pandaConfigService;

    /**
     * Retrieves the configuration for a specific Panda.
     *
     * @param id The ID of the Panda.
     * @return A ResponseEntity containing the Panda's configuration.
     */
    @GetMapping("/{id}")
    @Operation(
            description = "Get panda configuration",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "403",
                            description = "Unauthorized"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "Device with this parameters already exists"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Panda id",
                            required = true,
                            example = "1"
                    )
            }
    )
    public ResponseEntity<byte[]> getPandaConfig(@PathVariable Long id) {
        return pandaConfigService.downloadPandaConfig(id);
    }

    /**
     * Verifies a Panda configuration.
     *
     * @param pandaConfigJson The Panda configuration in JSON format.
     * @return A ResponseEntity containing the result of the verification.
     */
    @GetMapping("/verify")
    @Operation(
            description = "Verify panda configuration",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "403",
                            description = "Unauthorized"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "Device with this parameters already exists"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "pandaConfigJson",
                            description = "Panda configuration in json format",
                            required = true,
                            example = "{\n" +
                                    "  \"name\": \"Panda\",\n" +
                                    "  \"owner\": \" 1\" " +
                                    "  \"uuid\": \"1234567890\",\n" +
                                    "  \"api_key\": \"1234567890\"\n" +
                                    "}"
                    )
            })
    public ResponseEntity<ApiResponseDto<?>> verifyPandaConfig(String pandaConfigJson) {
        return pandaConfigService.verifyPandaConfig(pandaConfigJson);
    }
}