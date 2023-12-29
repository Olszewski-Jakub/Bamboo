package live.olszewski.bamboo.panda.dataPackets.collection;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "#{apiConfig.pandaDataCollectionPath}")
@Tag(name = "Panda Data Collection", description = "Endpoint to acquire panda data collection")
public class DataCollectionController {

    @Autowired
    private DataCollectionService dataCollectionService;


    @PostMapping
    @Operation(
            description = "Send data packet",
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
            },
            parameters = {
                    @Parameter(
                            name = "dataPacketReceive",
                            description = "Data packet",
                            required = true,
                            example = "{\"pandaId\":1,\"data\":\"10\"}"
                    )
            }
    )
    public ResponseEntity<ApiResponseDto<?>> sendDataPacket(@Parameter int peopleCount) {
        return dataCollectionService.sendDataPacket(peopleCount);
    }


}
