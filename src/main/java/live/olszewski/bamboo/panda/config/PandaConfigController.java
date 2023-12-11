package live.olszewski.bamboo.panda.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "#{apiConfig.pandaConfigPath}")
@Tag(name = "Panda Configuration", description = "Endpoint to acquire panda configuration")
public class PandaConfigController {

    @Autowired
    private PandaConfigService pandaConfigService;

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
}
