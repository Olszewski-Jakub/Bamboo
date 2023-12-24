package live.olszewski.bamboo.panda.device;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "#{apiConfig.pandaDevicePath}")
@Tag(name = "Panda", description = "Panda related endpoints")
public class PandaController {

    @Autowired
    private PandaService pandaService;

    @GetMapping(path = "/all")
    @Operation(
            description = "Get all panda devices",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Unauthorized"
                    )

            }

    )
    public ResponseEntity<ApiResponseDto<?>> getAllPandaDevices() {
        return pandaService.getAllPandaDevices();
    }

    @GetMapping()
    @Operation(
            description = "Get all panda devices by owner",
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
                            description = "Device with this parameters already exists"
                    )
            }
    )
    public ResponseEntity<ApiResponseDto<?>> getPandaDEviesByOwner() {
        return pandaService.getPandaDevicesByOwner();
    }

}
