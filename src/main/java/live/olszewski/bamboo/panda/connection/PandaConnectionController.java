package live.olszewski.bamboo.panda.connection;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import live.olszewski.bamboo.panda.connection.models.PandaStatus;
import live.olszewski.bamboo.panda.connection.models.PandaStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing Panda device connections.
 */
@RestController
@RequestMapping(path = "#{apiConfig. pandaConnectionStatusPath}")
@Tag(name = "Panda Connection status", description = "Endpoints related to panda status")
public class PandaConnectionController {

    @Autowired
    private PandaConnectionService pandaConnectionService;

    /**
     * Endpoint for checking the connection status of a Panda device using its UUID.
     *
     * @param UUID the UUID of the Panda device
     * @return a PandaStatusDto object representing the connection status of the Panda device
     */
    @GetMapping(path = "/uuid/{UUID}")
    @Operation(
            summary = "Check connection status with UUID",
            description = "Checks the connection status of a Panda device using its UUID.",
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
                            description = "Device with id {pandaId} does not exists"
                    )
            })
    public PandaStatusDto checkConnectionStatusWithUuid(@Parameter(description = "UUID of the Panda device") @PathVariable String UUID) {
        return pandaConnectionService.checkConnectionStatusWithUuid(UUID);
    }

    /**
     * Endpoint for checking the connection status of a Panda device using its ID.
     *
     * @param id the ID of the Panda device
     * @return a PandaStatusDto object representing the connection status of the Panda device
     */
    @GetMapping(path = "/id/{id}")
    @Operation(
            summary = "Check connection status with ID",
            description = "Checks the connection status of a Panda device using its ID.",
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
                            description = "Device with id {pandaId} does not exists"
                    )
            })
    public PandaStatusDto checkConnectionStatusWithId(@Parameter(description = "ID of the Panda device") @PathVariable String id) {
        return pandaConnectionService.checkConnectionStatusWithId(Long.parseLong(id));
    }

    /**
     * Endpoint for sending the connection status of a Panda device to the repository.
     *
     * @param UUID   the UUID of the Panda device
     * @param status the connection status of the Panda device
     */
    @PostMapping(path = "/{UUID}/{status}")
    @Operation(summary = "Send connection status",
            description = "Sends the connection status of a Panda device to the repository.",
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
                            description = "Device with id {pandaId} does not exists"
                    )
            }
    )
    public void sendConnectionStatus(@Parameter(description = "UUID of the Panda device") @PathVariable String UUID, @Parameter(description = "Connection status of the Panda device") @PathVariable String status) {
        pandaConnectionService.sendConnectionStatus(UUID, PandaStatus.CONNECTED);
    }

}